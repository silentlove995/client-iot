<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 12/11/2021
  Time: 3:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="upSystemCardURL" value="/api/user/status"/>
<c:url var="userURL" value="/user"/>
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
        <h1 class="text-center">Người dùng nạp thẻ để sử dụng thiết bị</h1>

        <form class="col-xs-12" id="formSubmit" action="" method="post">
            <div class="form-group">
                <div class="col-sm-3">
                    <label><b>Nhập Code Card: </b></label>
                </div>
                <div class="col-sm-9">
                    <input type="text" class="form-control input-sm" id="codeCard" name="codeCard"
                           value=""/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-3">
                    <label><b>Nhập Number Phone: </b></label>
                </div>
                <div class="col-sm-9">
                    <input type="text" class="form-control input-sm" id="phoneNumber" name="phoneNumber"
                           value=""/>
                </div>
            </div>

            <input type="hidden" class="form-control input-sm" id="isMobile" name="isMobile"
                   value="true"/>
        </form>
        <div class="row fa-align-justify ">
            <div class="col-md-12 text-center">
                <button class="btn btn-success" type="button" id="btnCard">
                    Nạp thẻ
                </button>
            </div>
        </div>
    </div>
</div>
<script>

    var file = [];
    $('#btnCard').click(function () {
        var formData = $('#formSubmit').serializeArray();
        var data = {};
        $.each(formData, function (index, v) {
            data['' + v.name + ''] = v.value;
        });
        debugger;
        upCard(data);
        console.log(data);
    });

    function upCard(data) {
        $.ajax({
            url: '${upSystemCardURL}',
            data: JSON.stringify(data),
            type: 'PUT',
            contentType: 'application/json',
            dataType: 'json',
            success: function (data) {
                window.location.href = "${userURL}?page=1&maxPageItem=10";
            },
            error: function (data) {
                showError(data);
            }
        });
    }

    $('#chooseDevice').click(function () {
        $("#choose").modal("show");
    });

    function loadData(id, name) {
        $('#deviceId').val(id);
        $('#name').val(name);
    }
</script>
</body>
</html>

