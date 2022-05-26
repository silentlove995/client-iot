<%--
  Created by IntelliJ IDEA.
  User: DatDV
  Date: 2/4/2021
  Time: 2:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<%--<c:url var="deviceAddOrUpdate" value="/admin/device/edit"/>--%>
<%--<c:url var="deviceAPICreate" value="/api/admin/device" />--%>
<c:url var="deviceListURL" value="/admin/card"/>
<c:url var="cardAPICreate" value="/api/admin/card"/>
<%--<c:url var="userAPISearch" value="/api/admin/user/by-condition" />--%>
<%--<c:url var="systemCardAPISearch" value="/api/admin/price/by-condition" />--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Edit Banner Page</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li>
                    <i class="ace-icon fa fa-list list-icon"></i>
                    Danh sách Card
                </li>
                <li>
                    Edit Page
                </li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <form class="col-xs-12" id="formSubmit" action="" method="post">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box table-filter">
                            <div class="widget-header">
                                <h4 class="widget-title">
                                    Edit & ADD
                                </h4>
                                <div class="widget-toolbar">
                                    <a href="#" data-action="collopse"><i class="ace-icon fa fa-chevron-up"></i></a>
                                </div>
                            </div>
                            <div class="widget-body">
                                <div class="widget-main">
                                    <div class="form-horizontal">

                                        <%-- Giá --%>

                                        <div class="form-group">
                                            <div class="col-sm-3">
                                                <label><b>Giá: </b></label>
                                            </div>
                                            <div class="col-sm-9">
                                                <input type="text" class="form-control input-sm" id="price" name="price"
                                                />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class="row text-center btn-addsp">
                <br>
                <button type="button" class="btn btn-success" id="btnAdd">Thêm Mới</button>
                <button type="button" class="btn btn-danger" id="btnBack">Hủy Bỏ</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" role="dialog" id="imgModalEditBanner">
    <div class="modal-dialog">
        <div class="modal-content"></div>
        <img class="img-responsive" src="" id="show-img">
    </div>
</div>

<script>


    $('#systemCardType').change(function (e) {
        e.preventDefault();

        var name = $(this).val();
        loadAjax(name);

    });

    function loadAjax(name) {
        $.ajax({
            url: '${systemCardAPISearch}' + '?systemCardType=' + name,
            type: "GET",
            contentType: 'application/json',
            datatype: "JSON",
            success: function (data) {
                console.log(data.details);
                var html = '';
                for (let i = 0; i < data.details.length; i++) {
                    html = html + "<option value='" + data.details[i].id + "'>" + data.details[i].price + "</option>";
                }
                $('#systemCards').html(html);

            },
            error: function (data) {

            }
        });
    }

    $('#chooseUser').click(function () {
        $("#choose").modal("show");
    });

    var file = [];
    $('#btnAdd').click(function () {
        var formData = $('#formSubmit').serializeArray();
        var data = {};

        $.each(formData, function (index, v) {
            data['' + v.name + ''] = v.value;
        });
        debugger;
        createDevice(data);

        console.log(data);
    });

    function loadData(id, name) {
        $('#userId').val(id);
        $('#fullName').val(name);

    }

    function createDevice(data) {
        $.ajax({
            url: '${cardAPICreate}',
            data: JSON.stringify(data),
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            success: function (data) {
                window.location.href = "${deviceListURL}?page=1&maxPageItem=10";
            },
            error: function (data) {
                showError(data);
            }
        });
    }


    if (window.File && window.FileReader && window.FileList && window.Blob) {
        document.getElementById('file').addEventListener('change', handleFileSelect, false);
    } else {
        alert('The File APIs are not fully supported in this browser.');
    }

    function handleFileSelect(evt) {
        var f = evt.target.files[0]; // FileList object
        var reader = new FileReader();
        // Closure to capture the file information.
        reader.onload = (function (theFile) {
            return function (e) {
                var binaryData = e.target.result;
                //Converting Binary Data to base 64
                var base64String = window.btoa(binaryData);
                //showing file converted to base64
                file.push(base64String);
            };
        })(f);
        // Read in the image file as a data URL.
        reader.readAsBinaryString(f);
    }

    function uploadFile(data) {
        $.ajax({
            url: '${bannerAPICreate}',
            data: JSON.stringify(data),
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            success: function (data) {
                window.location.href = "${bannerListURL}?page=1&maxPageItem=10";
            },
            error: function () {
                console.log("error");
            }
        });
    }

    function uploadFileUpdate(data) {
        $.ajax({
            url: '${bannerAPIUpdate}',
            data: JSON.stringify(data),
            type: 'PUT',
            contentType: 'application/json',
            dataType: 'json',
            success: function (data) {
                window.location.href = "${bannerListURL}?page=1&maxPageItem=10";
            },
            error: function () {
                console.log("error");
            }
        });
    }

    function showImageEditBanner(base64File, typeFile) {
        var img = "data:image/" + typeFile + ";base64, " + base64File + "";
        $("#show-img").attr('src', img);
        $("#imgModalEditBanner").modal('show');
    }

    $('#btnBack').click(function () {
        window.location.href = "${bannerListURL}?page=1&maxPageItem=10";
    })
</script>
</body>
</html>
