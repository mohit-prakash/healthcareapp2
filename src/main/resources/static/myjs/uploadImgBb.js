function uploadFile()
{
    var file=document.getElementById("fileOb");
    var form=new FormData();
    form.append("image",file.files[0]);
    var inputs = {
        url:"https://api.imgbb.com/1/upload?key=cdc1b0962cb80839f74138a28918707c",
        method:"POST",
        timeout:0,
        processData:false,
        mimeType:"multipart/form-data",
        contentType: false,
        data: form
    }
    $.ajax(inputs).done(function (response){
        var job=JSON.parse(response);
        $("#photoLoc").val(job.data.url);
    });
}