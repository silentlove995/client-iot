<%--
  Created by IntelliJ IDEA.
  User: DatDV
  Date: 2/4/2021
  Time: 1:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<%--<c:url var="deviceAPIUpdateStatus" value="/api/admin/device"/>--%>
<c:url var="updateStatusDevice" value="/api/admin/device/status"/>
<c:url var="home" value="/user"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title></title>
</head>
<body>
<div class="main-content d-flex justify-content-center">
    <div class="container">
        <h1 class="text-center">Quét mã QRCode để kích hoạt thiết bị</h1>
        <div class="row">
            <div class="col-md-12 text-center">
                <button class="btn btn-success" type="button" onclick="loadVideo()">
                    QRCode
                </button>
                <label>Để quét mã QRCode hãy để mã gần với camera của bạn</label>
            </div>
        </div>
    </div>
    <video id="preview"></video>
</div>

<div class="modal fade" id="webcam" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
            </div>
            <div class="modal-body">
                <p>Kích hoạt thiết bị thành công</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" id="close" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">


    function loadVideo(){
        let scanner = new Instascan.Scanner({ video: document.getElementById('preview') });
        scanner.addListener('scan', function (content) {
            var data = content.substr(41,);
            // console.log(data);
            $.ajax({
                url: '${updateStatusDevice}' + "/" + data,
                type: "PUT",

                success: function (data) {

                    $('#webcam').modal('show');
                    $('#close').click(function (){
                            window.location.href = "${home}";
                    });
                },
                error: function (data) {

                }
            });
        });
        Instascan.Camera.getCameras().then(function (cameras) {
            if (cameras.length > 0) {
                scanner.start(cameras[0]);
            } else {
                console.error('No cameras found.');
            }
        }).catch(function (e) {
            console.error(e);
        });
    }

</script>
</body>
</html>
