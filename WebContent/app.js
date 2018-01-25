
	var app = angular.module('myApp', [ 'ngRoute']);
	var usernameid = null;
	
	/*<------------>
	login controller
	
	*/
app.controller("LoginCtrl", function ($scope, $http) {  
		
		
		   $scope.login = function() {
		      
		   $http.get("http://localhost/projectmanager/rest/users/Login?username="+$scope.username+"&password="+$scope.password)
	       	 
		    .then(function(response) {
	    	 var arr = response.data;
	    	
	    	      if(arr!=null){
	    	  
	    		  if(arr.type=="customer"){
	    		     
	    		  usernameid = arr.id;
	    		 
	    			
	    		   var url = 'http://localhost/projectmanager/main.html#!/Customer';
	    		   window.location = url;
	    		   $( ".B1" ).css("display","block");
	    		   $( ".B3" ).css("display","block");
	                     
	    			     
	    	  };
	    		 
			       if(arr.type=="employee"){  
			    	
			       usernameid = arr.id;
			    
			       var url2 = 'http://localhost/projectmanager/main.html#!/Employee';
		    	   window.location = url2;
		    	   $( ".B1" ).css("display","block");
	    		   $( ".B5" ).css("display","block");
			          
			  };
	    		 
			       if(arr.type=="manager"){
			       var url3 = 'http://localhost/projectmanager/main.html#!/manager';
		    	   window.location = url3;
		    	   $( ".B1" ).css("display","block");
	    		   $( ".B2" ).css("display","block");
	    		   $( ".B4" ).css("display","block");
		    	   };
	    	  };  
	    	        
	   	              
	    	       if(arr.type==null){
		           alert("Insert UserName And Password Please");
		      };
	    });
	  }; 
	    
	   $scope.forgetpassword = function(){
	   $http.get("http://localhost/projectmanager/rest/users/forgetpass?username="+$scope.getnewuser)
	   .then(function(response) {
	   var forgotPassword=response.data;	
	   $scope.getnewuser="";
				
	     });
	   }
	    
	  });
	
	
	/*<------------>
	routing between pages
	
	*/
	
	app.config(function($routeProvider) {
		$routeProvider.when("/Employee", {
			templateUrl : "Employee.html",
			controller:"nineCtrl"
		})
		$routeProvider.when("/manager", {
			templateUrl:"manager.html",
			controller:"twoCtrl"
		 })
        $routeProvider.when("/Customer", {
			templateUrl : "Customer.html",
			controller:"sixCtrl"
		 })
	     $routeProvider.when("/EmployeeManagment", {
			templateUrl : "EmployeeManagment.html",
			controller: "oneCtrl"
		 })
	     $routeProvider.when("/", {
			templateUrl : "Login.html",
		     controller:"LoginCtrl"
		 })
	     $routeProvider.when("/CustomerManagment", {
			templateUrl : "CustomerManagment.html",
				controller: "threeCtrl"
         })
	     $routeProvider.when("/ProjectManagment", {
			templateUrl : "ProjectManagment.html",
			    controller:"fifthCtrl"
		 })
	     $routeProvider.when("/ManagerHourReportList", {
			templateUrl : "ManagerHourReportList.html",
			     controller:"sevenCtrl"
	     })
	     $routeProvider.when("/settings", {
	     templateUrl : "settings.html",
	     controller:"settingCtrl"
	    	
		 })
		 $routeProvider.when("/CustomerHourReportList", {
	     templateUrl : "CustomerHourReportList.html",
	     controller:"sixCtrl"
		 
		 })
		  $routeProvider.when("/EmployeeHourReportList", {
	     templateUrl : "EmployeeHourReportList.html",
	     controller:"nineCtrl"
		 
		 })
	});
	
     
	
	/*<------------>
	employee managment controller
	
	*/
	
	    app.controller("oneCtrl", function ($scope, $http) {
		$http.get("http://localhost/projectmanager/rest/employee/EmployeeList")
	    .then(function(response) {
	    $scope.employees = response.data;
	  	
	    
	     $scope.createEmployeeProject = function(){
	     $http.get("http://localhost/projectmanager/rest/employeeproject/create?employee="+$scope.employee+"&project="+$scope.project)
		 .then(function(response) {
	     var employeeproject = response.data; 
	    
	});
	    		
 };
	    	
        $http.get("http://localhost/projectmanager/rest/project/getAllProjects")
	    .then(function(response) {
	    $scope.projects = response.data;
	});
	       	
      //--------> buttons  ill not deleted.
	    	  $( ".B1" ).css("display","block");
			  $( ".B2" ).css("display","block");
			  $( ".B4" ).css("display","block");
			  
	          $scope.addEmployee = function(){		
	    	  $http.get("http://localhost/projectmanager/rest/employee/CreateEmployee?firstname="+$scope.firstname+"&lastname="+$scope.lastname+"&email="+$scope.email+"&phone="+$scope.phone+"&username="+$scope.username+"&password="+$scope.password)
		  
	    	 .then(function(response) {
	    	  var employees = response.data;
	    	
	    	  
	    	  $http.get("http://localhost/projectmanager/rest/employee/EmployeeList")
	  	      .then(function(response) {
	  	      $scope.employees = response.data;
	    	});
		});
	    
	      
		
	   };
	   
	    $scope.removeEmployee = function ($index) {
		var userId = $scope.employees[$index].user.id
	    $http.get("http://localhost/projectmanager/rest/users/DeleteUser?id="+userId)
	    .then(function(response) {
	     $scope.deleteEmployee = response.data;
	     console.log( $scope.deleteEmployee);
	  
		 if($scope.deleteEmployee.id==-1){
	    	  alert("You Cannot delete t his employee");
	      }else{
	    	  
	    	     alert("You  Deleteed an  employee ")
	    	   $http.get("http://localhost/projectmanager/rest/employee/EmployeeList")
			   .then(function(response) {
			   $scope.employees = response.data;   
		});
			 
		      $scope.firstname='';
		      $scope.lastname='';
		      $scope.email='';
		      $scope.phone='';
		      $scope.username='';
			  $scope.password='';
	   };
	    });
	    };
	   $scope.ShowObject = function($index){
		   $scope.firstname=$scope.employees[$index].firstname
		   $scope.lastname=$scope.employees[$index].lastname
		   $scope.email=$scope.employees[$index].email
		   $scope.phone=$scope.employees[$index].phone
		  
		   
		   $scope.update = function(){
		   $http.get("http://localhost/projectmanager/rest/employee/UpdateEmployee?id="+$scope.employees[$index].id+"&firstname="+$scope.firstname+"&lastname="+$scope.lastname+"&email="+$scope.email+"&phone="+$scope.phone)
		   .then(function(response){
		   $scope.updateresponse = response.data;
		    		 
		    if($scope.updateresponse.msg=="ok"){
		     $scope.firstname='';
			 $scope.lastname='';
			 $scope.email='';
			 $scope.phone='';
						
				$http.get("http://localhost/projectmanager/rest/employee/EmployeeList")
				.then(function(response) {
				$scope.employees = response.data;	  
						    	
				 });
		    	}
		      });
		    }	   
	      }
	   });
	});
	/*<------------>
	manager controller
	
	*/
	app.controller("twoCtrl", function ($scope, $http) {
		
		    $http.get("http://localhost/projectmanager/rest/project/getProjectByActive?isActive=true")
	        .then(function(response) {
	    	$scope.managerActiveProjects = response.data;
	    	 
	        $( ".B1" ).css("display","block");
			$( ".B2" ).css("display","block");
			$( ".B4" ).css("display","block");
	  
	    }); 
	         $http.get("http://localhost/projectmanager/rest/project/getProjectsAboutToFinish")
	         .then(function(response) {
	    	 $scope.managerFinishProjects = response.data;
	    	 
	    	  $( ".B1" ).css("display","block");
			  $( ".B2" ).css("display","block");
			  $( ".B4" ).css("display","block");
	    
	    	
       });
	 
	        $http.get("http://localhost/projectmanager/rest/customer/getStatus?isActive=true")
	       .then(function(response) {
	    	$scope.activeCustomers = response.data;
	    	 
	    	$( ".B1" ).css("display","block");
			$( ".B2" ).css("display","block");
			$( ".B4" ).css("display","block");
			  
	   });
	   
	       $http.get("http://localhost/projectmanager/rest/project/BigCustomer")
	       .then(function(response) {
	    	$scope.bigCustomers = response.data;
	    
	});
	    
	    
});
	/*<------------>
	customer managment controller
	
	*/
	    app.controller("threeCtrl", function ($scope, $http) {
		$http.get("http://localhost/projectmanager/rest/customer/CustomerList")
	    .then(function(response) {
	     
	    	  $( ".B1" ).css("display","block");
			  $( ".B2" ).css("display","block");
			  $( ".B4" ).css("display","block");
	    	  $scope.customers = response.data;
	
		
		    $scope.addCustomer = function(){		
			$http.get("http://localhost/projectmanager/rest/customer/createCustomer?companyname="+$scope.companyname+"&companynumber="+$scope.companynumber+"&contactname="+$scope.contactname+"&email="+$scope.email+"&phone="+$scope.phone+"&username="+$scope.username+"&password="+$scope.password)
			.then(function(response) {
			var customer = response.data;
		           console.log(customer);
			$http.get("http://localhost/projectmanager/rest/customer/CustomerList")
		    .then(function(response) {
		    $scope.customers = response.data;
		    });
			
		  });
		  
	};
		 $scope.RemoveCustomer = function ($index) {
			 
			  var userId = $scope.customers[$index].user.id
			  var r = confirm("are you sure  you want remove customer?");
			  if (r == true) {
			  $http.get("http://localhost/projectmanager/rest/users/DeleteUser?id="+userId)
		      .then(function(response) {
		    	  $scope.deleteCustomer = response.data;
		    	  
		            console.log($scope.deleteCustomer);
				     
			      if($scope.deleteCustomer.id==-1){
			    	  alert("You Cannot Delete This Customer");
			      }else{
			    	   $http.get("http://localhost/projectmanager/rest/customer/CustomerList")
					   .then(function(response) {
					   $scope.customers = response.data;   
					   });
					   $scope.customers = response.data;
					   $scope.companyname='';
					   $scope.companynumber='';
					   $scope.contactname='';
					   $scope.email='';
					   $scope.phone='';
					   $scope.username='';
					   $scope.password='';
			      
			      };	  
			    	
		      
			      });
		 };
			      };
			   });	
		 
			   $scope.ShowObject = function($index){
			   $scope.companyname=$scope.customers[$index].companyname
			   $scope.companynumber=$scope.customers[$index].companynumber
			   $scope.contactname=$scope.customers[$index].contactname
			   $scope.email=$scope.customers[$index].email
			   $scope.phone=$scope.customers[$index].phone
			   
			   $scope.update = function(){
			   $http.get("http://localhost/projectmanager/rest/customer/updatecustomer?id="+$scope.customers[$index].id+"&companyname="+$scope.companyname+"&companynumber="+$scope.companynumber+"&contactname="+$scope.contactname+"&email="+$scope.email+"&phone="+$scope.phone)
			  .then(function(response){
			   $scope.updateresponse = response.data;
				    		 
			  if($scope.updateresponse.msg=="ok"){
			  $scope.companyname='';
			  $scope.companynumber='';
			  $scope.contactname='';
			  $scope.email='';
			  $scope.phone='';
								
			  $http.get("http://localhost/projectmanager/rest/customer/CustomerList")
			    .then(function(response) {
			  $scope.customers = response.data;	  
								    	
						 });
				      }
				  });
			   }	   
			 }
	    
	  });

	/*<------------>
	project managment controller
	
	*/
	    app.controller("fifthCtrl", function ($scope, $http) {
	    	
	     $http.get("http://localhost/projectmanager/rest/project/getAllProjects")
	    .then(function(response) {
	     $scope.projects = response.data;
	          console.log($scope.projects);
	  
                $scope.createEmployeeProject = function(){
	    	    $http.get("http://localhost/projectmanager/rest/employeeproject/create?employee="+$scope.employee+"&project="+$scope.project)
			   .then(function(response) {
			    var employeeproject = response.data;
			    
			   });
	    	 };  
	    	    
	    	 
	     
	    	 $http.get("http://localhost/projectmanager/rest/employee/EmployeeList")
		    .then(function(response) {
		     $scope.employees = response.data;	  
	         });
	    	 $http.get("http://localhost/projectmanager/rest/customer/CustomerList")
			 .then(function(response) {
			 $scope.customers = response.data;
			 });
	     
	    	$( ".B1" ).css("display","block");
			$( ".B4" ).css("display","block");
			
			
			
		
		    $scope.addProject = function(){		
			$http.get("http://localhost/projectmanager/rest/project/CreateProject?projectname="+$scope.projectname+"&customer="+$scope.customer+"&customerprojectmanager="+$scope.customerprojectmanager+"&projectmanageremail="+$scope.projectmanageremail+"&projectmanagerphone="+$scope.projectmanagerphone+"&startdate="+$.datepicker.formatDate('yy-mm-dd',$scope.startdate)+"&enddate="+$.datepicker.formatDate('yy-mm-dd',$scope.enddate))
		    .then(function(response) {
			   console.log($scope.startdate);
			
			$scope.projectname='';
			$scope.customer='';
			$scope.customerprojectmanager='';
			$scope.projectmanageremail='';
			$scope.projectmanagerphone='';
			$scope.startdate='';
			$scope.enddate='';
			
			 $http.get("http://localhost/projectmanager/rest/project/getAllProjects")
			 .then(function(response) {
			 $scope.projects = response.data;
			
		   });
			 
		});
		    };
		    $scope.RemoveProject = function ($index) {
		    	/* var r = confirm("are you sure  you want to remove project?");
				  if (r == true) { */
			$http.get("http://localhost/projectmanager/rest/project/DeleteProject?id="+$scope.projects[$index].id)
		    .then(function(response) {	
		     $scope.deleteProject = response.data;	
		     
		     if($scope.deleteProject.id==-1){
		    	  alert("You Cannot Delete This Project");
		      }else{
		    	   $http.get("http://localhost/projectmanager/rest/project/getAllProjects")
				   .then(function(response) {
				   $scope.projects = response.data;   
				   });
		     
			      $scope.projectname='';
			      $scope.customer='';
			      $scope.customerprojectmanager='';
			      $scope.projectmanageremail='';
			      $scope.projectmanagerphone='';
			      $scope.startdate='';
			      $scope.enddate='';
		    };
		     });
		    };
		
		  
	     $scope.ShowObject = function($index){
	    	 
			   $scope.projectname=$scope.projects[$index].projectname
			   $scope.customer=$scope.projects[$index].customer
			   $scope.customerprojectmanager=$scope.projects[$index].customerprojectmanager
			   $scope.projectmanageremail=$scope.projects[$index].projectmanageremail
			   $scope.projectmanagerphone=$scope.projects[$index].projectmanagerphone
			   $scope.startdate=$scope.projects[$index].startdate
			   $scope.enddate=$scope.projects[$index].enddate
	    
	              $scope.update = function(){
	    	      $http.get("http://localhost/projectmanager/rest/project/UpdateProject?id="+$scope.projects[$index].id+"&porjectname="+$scope.projectname+"&customer="+$scope.customer+"&customerprojectmanager="+$scope.customerprojectmanager+"&projectmanageremail="+$scope.projectmanageremail+"&projectmanagerphone="+$scope.projectmanagerphone+"&startdate="+$scope.startdate+"&enddate="+$scope.enddate)
	    	     .then(function(response){
	    		  $scope.updateresponse = response.data;
	    		 
	    		 if($scope.updateresponse.msg=="ok"){
	    			 $scope.projectname='';
					 $scope.customer='';
					 $scope.customerprojectmanager='';
					 $scope.projectmanageremail='';
					 $scope.projectmanagerphone='';
					 $scope.startdate='';
					 $scope.enddate=''; 	
					 
					    $http.get("http://localhost/projectmanager/rest/project/getAllProjects")
					   .then(function(response) {
					    $scope.projects = response.data;
					  });
	    		
	    		  }
	    		  
	    
	    	  });
	       }
	     }	   
	 });
  });
	   
	/*<------------>
	customer controller
	
	*/
		   app.controller("sixCtrl", function ($scope, $http) {
			
		 
		   $http.get("http://localhost/projectmanager/rest/project/activeproject?isActive=true&id="+usernameid)
	      .then(function(response) {
	    	//--------> buttons  ill not deleted.
	    	$scope.customerActiveProjects = response.data;
	    	
	    	
	    	$( ".B1" ).css("display","block");
			$( ".B3" ).css("display","block");
                 
	    });
		
		  $http.get("http://localhost/projectmanager/rest/hourreport/getCustomerHr?id="+usernameid)
	      .then(function(response) {
	      $scope.customerhr = response.data;
	    
	   
	        $( ".B1" ).css("display","block");
			$( ".B3" ).css("display","block");
         });
		  
		 $http.get("http://localhost/projectmanager/rest/project/projectNameById?id="+usernameid)
		 .then(function(response){
		  $scope.projects = response.data;
		 
			 
		 });
		 
		  $scope.showresults=function(){
				
				$scope.date1 = $.datepicker.formatDate('yy-mm-dd',$scope.date);
				$http.get("http://localhost/projectmanager/rest/hourreport/getByYearAndMonth?yearandmonth="+$scope.date1+"&employee="+$scope.employee+"&customer="+usernameid+"&project="+$scope.project)
				.then(function(response){
		 	    $scope.customerhr = response.data;
				console.log( $scope.customerhr);
				 
		  });
			
			    }
				     if($scope.employee==null){
					   $scope.employee = 0;
					   
					
					}
					if($scope.project==null){
					   $scope.project = 0;
					  
				   }
						

		      });
	
	/*<------------>
	managerPage controller
	
	*/
	          app.controller("sevenCtrl", function ($scope, $http) {
		      $http.get("http://localhost/projectmanager/rest/hourreport/getallhourreports")
	         .then(function(response) {
	          $scope.managerhr = response.data;
	  });	
	    	 
	    	$( ".B1" ).css("display","block");
		    $( ".B2" ).css("display","block");
			$( ".B4" ).css("display","block");
			
			   $http.get("http://localhost/projectmanager/rest/employee/EmployeeList")
			   .then(function(response) {
			   $scope.employees = response.data;	  
					    	
			 });
			
			    $http.get("http://localhost/projectmanager/rest/project/getAllProjects")
			   .then(function(response) {
			    $scope.projects = response.data;
			  });
			 
			   $http.get("http://localhost/projectmanager/rest/customer/CustomerList")
			   .then(function(response) {
			   $scope.customers = response.data;
			
			 });
			
			
			
	    
		
		$scope.showresults=function(){
			
			$scope.date1 = $.datepicker.formatDate('yy-mm',$scope.date);
			$http.get("http://localhost/projectmanager/rest/hourreport/getByYearAndMonth?yearandmonth="+$scope.date1+"&employee="+$scope.employee+"&customer="+$scope.customer+"&project="+$scope.project)
			.then(function(response){
	 	    $scope.managerhr = response.data;
			
			 
			 });
		
		  }
			     if($scope.employee==null){
				   $scope.employee = 0;
					console.log($scope.employee)
				
				}
				if($scope.customer==null){
					$scope.customer = 0;
					console.log($scope.customer)
				}
				if($scope.project==null){
					$scope.project = 0;
					console.log($scope.project)
				}
			 
	 });
	
	/*<------------>
	employee controller
	
	*/
	    app.controller("nineCtrl", function ($scope, $http) {
			
		        $http.get("http://localhost/projectmanager/rest/hourreport/getSevenDaysBack?id="+usernameid)
		        .then(function(response) {
		    	$scope.sevenD = response.data;
		        var hrnum=$scope.sevenD.length;
		    	
		    	
		   
		    	
		    	$http.get("http://localhost/projectmanager/rest/employeeproject/getProjectName?id="+usernameid)
		        .then(function(response) {
		        $scope.projectname = response.data;	
		      
		        
		      });
		    	
		    	  $http.get("http://localhost/projectmanager/rest/hourreport/getEmployeeHr?id="+usernameid)
				  .then(function(response) {
				  $scope.employeehr = response.data;
				
				    	
			});
		      
		     /*    $http.get("http://localhost/projectmanager/rest/properties/gethours")
		         .then(function(response) {
		    	 $scope.hours = response.data;
		         var gethour = $scope.hours.split(",");
		         $scope.a = gethour[0].replace("-",":");
		         $scope.b = gethour[1].replace("-",":");
               
            }); 
			*/
		            $http.get("http://localhost/projectmanager/rest/properties/getDays")
		           .then(function(response) {
		        	var getdays = response.data;
		        	console.log(getdays);
		        	 
		        	var array = getdays.split(",");
		        	console.log(array);
		                   
		                  
		                    $('#someid').datepicker({beforeShowDay:function(date) {
		        	    	 
		        	         var day = date.getDay();
		        	         console.log("ad");
			        	     var newvar = array[day].includes(true);
			        	    return [newvar,' ',' '];
			        	    console.log(newvar);
		                    }   
		                  });
		                
		           }); 
		        	
		         
		           
			       $scope.createHourReport = function(){
			    	
			    	if($scope.starthour.getHours()<10){
			    		$scope.one = "0"+$scope.starthour.getHours();
			    	}else{
			    		$scope.one = $scope.starthour.getHours();
			    		
			    	}
			    	if($scope.starthour.getMinutes()<10){
			    		$scope.two = "0"+$scope.starthour.getMinutes();
			    	}else{
			    		$scope.two = $scope.starthour.getMinutes();
			    	}
			    	if($scope.endhour.getHours()<10){
			    		$scope.three = "0"+$scope.endhour.getHours();
			    	}else{
			    		$scope.three = $scope.endhour.getHours();
			    		
			    	}
			    	if($scope.endhour.getMinutes()<10){
			    		$scope.four = "0"+$scope.endhour.getMinutes();
			    	}else{
			    		$scope.four = $scope.endhour.getMinutes();
			    	}
			    	
			    	
			        $scope.eid = $scope.projectname[0].employee.id	
			        var date = $('#someid').datepicker({dateFormat:'yy-mm-dd'}).val();
			        var goodday = date.split("/");
			        var newone = goodday[2]+"-"+goodday[0]+"-"+goodday[1];
			        console.log(newone);
			        var timeone = newone+" "+$scope.one+":"+$scope.two;
			        var timetwo = newone+" "+$scope.three+":"+$scope.four;
			        $http.get("http://localhost/projectmanager/rest/hourreport/createhourreport?employee="+$scope.eid+"&project="+$scope.project+"&starthour="+timeone+"&endhour="+timetwo+"&description="+$scope.description)	
			        .then(function(response) {
			        $scope.createhr = response.data;
			       
			    
			        $http.get("http://localhost/projectmanager/rest/hourreport/getSevenDaysBack?id="+usernameid)
			        .then(function(response) {
			        $scope.sevenD = response.data;   
			       
			  });
			       
			       
			       $http.get("http://localhost/projectmanager/rest/hourreport/getEmployeeHr?id="+usernameid)
				   .then(function(response) {
				   $scope.employeehr = response.data;
				 
			    });
			    
			     
					$scope.project='';
					$scope.starthour='';
					$scope.endhour='';
					$scope.date='';
					$scope.description=''; 
			     });	
		      } 
	      });	
		        $scope.showresults=function(){
					
					$scope.date1 = $.datepicker.formatDate('yy-mm-dd',$scope.date);
					$http.get("http://localhost/projectmanager/rest/hourreport/getByYearAndMonth?yearandmonth="+$scope.date1+"&employee="+$scope.employee+"&customer="+$scope.customer+"&project="+$scope.project)
					.then(function(response){
			 	    $scope.employeehr = response.data;
				
					 
			 });
				
				  }
					     if($scope.employee==null){
						   $scope.employee = 0;
							console.log($scope.employee)
						
						}
						if($scope.customer==null){
							$scope.customer = 0;
							console.log($scope.customer)
						}
						if($scope.project==null){
							$scope.project = 0;
							console.log($scope.project)
					   }
					     
		        
	       });
	
	/*<------------>
	logout controller
	
	*/
	    app.controller("myCtrl", function($scope,$http){
		$scope.logout = function(){
			var r =  confirm("are you sure you want to log out?");
			
			if(r==true){
				var logout = 'http://localhost/projectmanager/main.html#!/'
				    window.location = logout; 
			}else{
				
			}
		
			  
		 $( ".B1" ).css("display","none");
		 $( ".B2" ).css("display","none");
		 $( ".B3" ).css("display","none");
		 $( ".B4" ).css("display","none");
		 $( ".B5" ).css("display","none");	 
		};
	 
		
	});
	    
	   
	    /*<------------>
		settings controller
		
		*/
	            app.controller("settingCtrl",function($scope,$http){
	            	$( ".B1" ).css("display","block");
					$( ".B4" ).css("display","block");
	            	$scope.addHour = function(){
		    	
		    	if($scope.starthour.getHours()<10){
		    		$scope.one = "0"+$scope.starthour.getHours();
		    	}else{
		    		$scope.one = $scope.starthour.getHours();
		    		
		    	}
		    	if($scope.starthour.getMinutes()<10){
		    		$scope.two = "0"+$scope.starthour.getMinutes();
		    	}else{
		    		$scope.two = $scope.starthour.getMinutes();
		    	}
		    	if($scope.endhour.getHours()<10){
		    		$scope.three = "0"+$scope.endhour.getHours();
		    	}else{
		    		$scope.three = $scope.endhour.getHours();
		    		
		    	}
		    	if($scope.endhour.getMinutes()<10){
		    		$scope.four = "0"+$scope.endhour.getMinutes();
		    	}else{
		    		$scope.four = $scope.endhour.getMinutes();
		    	}
		    	
		    	  
		    	 var time = $scope.one+"-"+$scope.two+","+$scope.three+"-"+$scope.four;
				   
		    	 console.log(time); 
				 $http.get("http://localhost/projectmanager/rest/properties/sethours?worktime="+time)
		 	    .then(function(response) {
		 	     $scope.settings = response.data;
		 	     console.log($scope.settings);
		 	     
		 	      
		 	     
		    	
		   });
	   };
	   
	    $scope.setDays = function(){


        	    
       var sunday = $('#sunday').is(':checked')?true:false ;
   	   var monday = $('#monday').is(':checked')?true:false;
   	   var thirdday = $('#thirdday').is(':checked')?true:false;
   	   var wednesday = $('#wednesday').is(':checked')?true:false;
   	   var Thursday = $('#Thursday').is(':checked')?true:false;
   	   var friday = $('#friday').is(':checked')?true:false;
   	   var saturday = $('#saturday').is(':checked')?true:false;
   	  
   	    
   	    
   	    
   	   
   	    alert(monday+" "+"monday");
       alert(thirdday+" "+"thirdday");
   	   alert(wednesday+" "+"wednesday");
   	   alert(friday+"  "+"friday");
   	   alert(saturday+" "+"saturday");
    	 
   	  
    	     $http.get("http://localhost/projectmanager/rest/properties/setdays?sunday="+sunday+"&monday="+monday+"&thirdday="+thirdday+"&wednesday="+wednesday+"&Thursday="+Thursday+"&friday="+friday+"&saturday="+saturday)
	 	    .then(function(response) {
	 	     $scope.days = response.data;
	 	     var newvar =  $scope.days;
	 	     console.log($scope.days);	 
    	 
       	   
	 	     
     });
     
  }; 
});