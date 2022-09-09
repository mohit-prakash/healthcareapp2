 $(document).ready(function(){
            //1. hide error section
            $("#specCodeError").hide();
            $("#specNameError").hide();
            $("#specNoteError").hide();

            //2. define error variable
            var specCodeError=false;
            var specNameError=false;
            var specNoteError=false;

            //3. define validate function
            function validateSpecCode(){
                var val=$("#specCode").val();
                var exp=/^[A-Z]{4,10}$/;
                if (val=="")
                {
                    $("#specCodeError").show();
                    specCodeError=false;
                    $("#specCodeError").html("* <b>Code</b> can not be empty!!");
                    $("#specCodeError").css("color","red");
                }
                else if (!exp.test(val))
                {
                    $("#specCodeError").show();
                    specCodeError=false;
                    $("#specCodeError").html("* <b>Code</b> must be 4 to 10 chars !!");
                    $("#specCodeError").css("color","red");
                }
                else
                {
                   $.ajax({
						url:"checkcode",
						data: {"code": val},
						success: function(respTxt){
							if(respTxt!="")
							{
								 $("#specCodeError").show();
                			    specCodeError=false;
                    				$("#specCodeError").html(respTxt);
                    				$("#specCodeError").css("color","red");
							}
							else
							{
								 $("#specCodeError").hide();
                			    specCodeError=true;
							}
						}
					});
                }
                return specCodeError;
            }

            function validateSpecName(){
                var val=$("#specName").val();
                var exp=/^[A-Za-z\s]{4,60}$/;
                if (val=="")
                {
                    $("#specNameError").show();
                    specNameError=false;
                    $("#specNameError").html("* <b>Name</b> can not be empty!!");
                    $("#specNameError").css("color","red");
                }
                else if (!exp.test(val))
                {
                    $("#specNameError").show();
                    specNameError=false;
                    $("#specNameError").html("* <b>Name</b> must be 4 to 60 chars !!");
                    $("#specNameError").css("color","red");
                }
                else
                {
                    $("#specNameError").hide();
                    specNameError=true;
                }
                return specNameError;
            }

            function validateSpecNote(){
                var val=$("#specNote").val();
                var exp=/^[A-Za-z0-9\.\,\'\s\?]{4,250}$/;
                if (val=="")
                {
                    $("#specNoteError").show();
                    specNoteError=false;
                    $("#specNoteError").html("* <b>Note</b> can not be empty!!");
                    $("#specNoteError").css("color","red");
                }
                else if (!exp.test(val))
                {
                    $("#specNoteError").show();
                    specNoteError=false;
                    $("#specNoteError").html("* <b>Note</b> must be 4 to 250 chars !!");
                    $("#specNoteError").css("color","red");
                }
                else
                {
                    $("#specNoteError").hide();
                    specNoteError=true;
                }
                return specNoteError;
            }

            //action event
            $("#specCode").keyup(function(){
                //Converting into uppercase
                 $(this).val($(this).val().toUpperCase());
                validateSpecCode();
            });

            $("#specName").keyup(function(){
                validateSpecName();
            });

            $("#specNote").keyup(function(){
                validateSpecNote();
            });

            //5. on Submit
            $("#specReg").submit(function(){
                validateSpecCode();
                validateSpecName();
                validateSpecNote();
                if (specCodeError && specNameError && specNoteError)
                    return true;
                else
                    return false;
            });
        });