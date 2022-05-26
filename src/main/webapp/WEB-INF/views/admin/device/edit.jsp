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
<c:url var="deviceAddOrUpdate" value="/admin/device/edit"/>
<c:url var="deviceAPICreate" value="/api/admin/device"/>
<c:url var="deviceListURL" value="/admin/device"/>
<c:url var="deviceAPIUpdate" value="/api/admin/device"/>
<c:url var="userAPISearch" value="/api/admin/user/by-condition"/>
<c:url var="priceLevelAPISearch" value="/api/admin/price/by-condition"/>
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
                    Danh sách Thiết bị
                </li>
                <li>
                    Edit Page
                </li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <form class="col-xs-12" id="formSubmit" action="${deviceAddOrUpdate}" method="post" modelAttribute="model">
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

                                        <%-- Tên Thiết bị --%>

                                        <div class="form-group">
                                            <div class="col-sm-3">
                                                <label><b>Tên Thiết bị: </b></label>
                                            </div>
                                            <div class="col-sm-9">
                                                <input type="text" class="form-control input-sm" id="name" name="name"
                                                       value="${deviceDTO.name}"/>
                                            </div>
                                        </div>

                                        <%-- Code Thiết bị--%>

                                        <div class="form-group">
                                            <div class="col-sm-3">
                                                <label><b>Code: </b></label>
                                            </div>
                                            <div class="col-sm-3">
                                                <c:if test="${empty deviceDTO.code}">
                                                    <input type="text" class="form-control input-sm" id="code"
                                                           name="code"
                                                           value="${deviceDTO.code}"/>
                                                </c:if>
                                                <c:if test="${not empty deviceDTO.code}">
                                                    <input readonly type="text" class="form-control input-sm" id="code"
                                                           name="code"
                                                           value="${deviceDTO.code}"/>
                                                </c:if>
                                            </div>
                                        </div>
                                        <%-- Device Price --%>

                                        <div class="form-group">
                                            <div class="col-sm-3">
                                                <label><b>Device Price: </b></label>
                                            </div>
                                            <div class="col-sm-9">
                                                <select id="priceLevelType">
                                                    <option value="">--Chọn kiểu thanh toán--</option>
                                                    <option value="MINUTE">MINUTE</option>
                                                    <option value="TRAIL">TRAIL</option>
                                                </select>
                                                <select name="priceLevels" id="priceLevels">
                                                    <option value="">--Chọn giá--</option>

                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-sm-3">
                                                <label><b>User: </b></label>
                                            </div>
                                            <div class="col-sm-9">
                                                <input type="hidden" name="userId" id="userId"
                                                       value="${deviceDTO.user.id}">
                                                <input type="text" readonly id="fullName"
                                                       value="${deviceDTO.user.username}">
                                                <button type="button" class="btn btn-primary btn-lg" id="chooseUser">
                                                    Choose User
                                                </button>
                                            </div>
                                        </div>

                                        <div class="modal fade" id="choose" tabindex="-1" role="dialog"
                                             aria-labelledby="exampleModalLabel"
                                             aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title">Search user:</h4>
                                                        <input type="text" id="userNameSearch">
                                                        <button type="button" class="btn btn-success" id="findByName">
                                                            Search
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <table class="table table-striped">
                                                            <thead>
                                                            <tr>
                                                                <th>UserName</th>
                                                                <th>FullName</th>
                                                                <th>Email</th>
                                                                <th>NumberPhone</th>
                                                                <th>Choose</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <c:forEach var="item" items="${userOutput.list}">
                                                                <tr>
                                                                    <td>${item.username}</td>
                                                                    <td>${item.fullName}</td>
                                                                    <td>${item.email}</td>
                                                                    <td>${item.numberPhone}</td>
                                                                    <td>
                                                                        <button class="btn btn-xs btn-primary btn-edit"
                                                                                data-toggle="tooltip"
                                                                                title="Turn On The Device"
                                                                                data-dismiss="modal"
                                                                                onclick="loadData(${item.id},'${item.fullName}')"
                                                                                data-dismiss="modal">
                                                                            <i class="ace-icon fa fa-check bigger-120"></i>
                                                                        </button>
                                                                    </td>
                                                                </tr>

                                                            </c:forEach>
                                                            </tbody>
                                                        </table>

                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-danger"
                                                                data-dismiss="modal">Close
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <input type="text" value="${deviceDTO.id}" hidden id="id"/>

            </form>
            <div class="row text-center btn-addsp">
                <br>
                <c:if test="${empty deviceDTO.id}">
                    <button type="button" class="btn btn-success" id="btnAddOrUpdateBuilding">Thêm Mới</button>
                    <button type="button" class="btn btn-danger" id="btnBack">Hủy Bỏ</button>
                </c:if>
                <c:if test="${not empty deviceDTO.id}">
                    <button type="button" class="btn btn-success" id="btnAddOrUpdateBuilding">Cập Nhật</button>
                    <button type="button" class="btn btn-danger" id="btnBack">Hủy Bỏ</button>
                </c:if>
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

    $('#findByName').click(function () {
        var username = $('#userNameSearch').val();

        $.ajax({
            type: 'GET',
            url: '${userAPISearch}' + '?username=' + username,
            contentType: 'application/json',
            datatype: "JSON",
            success: function (data) {
                console.log(data);
                var html = '';
                if (data.details.list.length != 0) {
                    for (let i = 0; i < data.details.list.length; i++) {
                        var fullname = "'" + data.details.list[i].fullName + "'";
                        html = html + '<tr> <td>' + data.details.list[i].username + '</td> <td>' + data.details.list[i].fullName + '</td> <td> ' + data.details.list[i].email + '</td> <td>' +
                            data.details.list[i].numberPhone + '</td> <td> <button class="btn btn-xs btn-primary btn-edit"  data-toggle="tooltip" title="Turn On The Device" data-dismiss="modal" ' +
                            'onclick=' + '"loadData(' + data.details.list[i].id + ',' + fullname + ')"' + ' data-dismiss="modal">' +
                            '<i class="ace-icon fa fa-check bigger-120"></i> </button> </td> </tr>';

                        $("tbody").html(html);
                    }
                }
            }
        });

    });

    $('#priceLevelType').change(function (e) {
        e.preventDefault();

        var name = $(this).val();
        loadAjax(name);

    });

    function loadAjax(name) {
        $.ajax({
            url: '${priceLevelAPISearch}' + '?priceLevelType=' + name,
            type: "GET",
            contentType: 'application/json',
            datatype: "JSON",
            success: function (data) {
                console.log(data.details);
                var html = '';
                for (let i = 0; i < data.details.length; i++) {
                    html = html + "<option value='" + data.details[i].id + "'>" + data.details[i].price + "</option>";
                }
                $('#priceLevels').html(html);

            },
            error: function (data) {

            }
        });
    }

    $('#chooseUser').click(function () {
        $("#choose").modal("show");
    });

    var file = [];
    $('#btnAddOrUpdateBuilding').click(function () {
        var id = $('#id').val();
        var formData = $('#formSubmit').serializeArray();
        var priceLevelIds = [];
        var device = {};
        var data = {device, priceLevelIds};

        $.each(formData, function (index, v) {
            if (v.name == 'priceLevels') {
                data.priceLevelIds.push(Number(v.value));
            } else {
                data.device['' + v.name + ''] = v.value;
            }
        });
        debugger;
        if (id == '') {
            createDevice(data);
        } else {
            updateDevice(data, id);
        }
        console.log(data);
    });

    function loadData(id, name) {
        $('#userId').val(id);
        $('#fullName').val(name);

    }

    function createDevice(data) {
        $.ajax({
            url: '${deviceAPICreate}',
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

    function updateDevice(data, id) {
        let url = '${deviceAPIUpdate}' + '?id=' + id;
        $.ajax({
            url: url,
            data: JSON.stringify(data),
            type: 'PUT',
            contentType: 'application/json',
            dataType: 'json',
            success: function (data) {
                window.location.href = "${deviceListURL}?page=1&maxPageItem=10";
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

    $('#btnBack').click(function () {
        window.location.href = "${bannerListURL}?page=1&maxPageItem=10";
    })
</script>
</body>
</html>
