/**
 * 
 */
$(document).ready(function() {

		$('#w-input-search').autocomplete({
			serviceUrl: '/TalonSystem/jsonPosts',
			paramName: "postName",
			delimiter: ",",
		    transformResult: function(response) {
		    	
		        return {
		        	
		            suggestions: $.map($.parseJSON(response), function(item) {
		            	
		                return { value: item.postname, data: item.id };
		            })
		            
		        };
		        
		    }
		    
		});
		
		$("#stubnum").keydown(function (e) {
	        // Allow: backspace, delete, tab, escape, enter and .
	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
	             // Allow: Ctrl+A, Command+A
	            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || 
	             // Allow: home, end, left, right, down, up
	            (e.keyCode >= 35 && e.keyCode <= 40)) {
	                 // let it happen, don't do anything
	                 return;
	        }
	        // Ensure that it is a number and stop the keypress
	        if ((e.shiftKey || (e.keyCode < 49 || e.keyCode > 51)) && (e.keyCode < 97 || e.keyCode > 99)) {
	            e.preventDefault();
	        }
	    });
		
		$('#workat').autocomplete({
			serviceUrl: '/TalonSystem/jsonCompanies',
			paramName: "workat",
			delimiter: ",",
		    transformResult: function(response) {
		    	width=$('#workat').parent().width();
		    	$('.autocomplete-suggestions').width(width);
		        return {
		        	
		            suggestions: $.map($.parseJSON(response), function(item) {
		                return { value: item.callname, data: item.id };
		            })
		            
		        };
		        
		    }
		});
		$('#repworkat').autocomplete({
			serviceUrl: '/TalonSystem/jsonCompanies',
			paramName: "workat",
			delimiter: ",",
		    transformResult: function(response) {
		    	width=$('#repworkat').parent().width();
		    	$('.autocomplete-suggestions').width(width);
		        return {
		        	
		            suggestions: $.map($.parseJSON(response), function(item) {
		                return { value: item.callname, data: item.id };
		            })
		            
		        };
		        
		    }
		});
		$('#postsearch').autocomplete({
			serviceUrl: '/TalonSystem/jsonAllCompanies',
			paramName: "workat",
			delimiter: ",",
		    transformResult: function(response) {
		    	
		        return {
		        	
		            suggestions: $.map($.parseJSON(response), function(item) {
		            	
		                return { value: item.callname, data: item.id };
		            })
		            
		        };
		        
		    }
		    
		});
		$("#createpdf").click(function() {
		    // this function will get executed every time the #home element is clicked (or tab-spacebar changed)
		    if($(this).is(":checked")) // "this" refers to the element that fired the event
		    {
//		        alert('home is checked');
		        $("#genrepsubmit").attr("formtarget","_blank");
		        $("#genrepsubmit").attr("value","Созадть отчет");
		        
		        
		    }else{
//		    	alert('home is unchecked');
		    	$("#genrepsubmit").attr("formtarget","_self");
		    	$("#genrepsubmit").attr("value","Поиск");
		    }
		});
		
		
	});