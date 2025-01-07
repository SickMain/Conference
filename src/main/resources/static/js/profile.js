$(document).ready(function () {

    function showChangeTitlePhotoForm(){
        $("#change-title-photo").hide();
        $("#drop-title-photo-form").hide();
        $("#change-title-photo-form").slideDown();
    }

    $("#change-title-photo").click(function(){
        showChangeTitlePhotoForm();
    });
        


});

