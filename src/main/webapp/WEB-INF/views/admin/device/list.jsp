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
<c:url var="deviceAPIUpdateStatus" value="/api/admin/device/status"/>
<c:url var="deviceAPIDelete" value="/api/admin/device"/>
<c:url var="deviceSearchURL" value="/admin/device"/>
<c:url var="deviceAPIFindByCode" value="/api/admin/device"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Danh Sách Thiết Bị</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Danh Sách Thiết Bị</a>
                </li>
                <li>
                    Danh Sách Thiết Bị
                </li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <form:form class="col-xs-12" action="${deviceSearchURL}" id="formSubmit" method="get"
                           modelAttribute="model">
                    <div class="widget-box table-filter">
                        <div class="widget-header">
                            <h4 class="widget-title">
                                Tìm Kiếm
                            </h4>
                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse"><i class="ace-icon fa fa-chevron-up"></i></a>
                            </div>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <div class="col-sm-6">
                                            <label><b>Tên Device: </b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="name"
                                                       value="${model.name}"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <label><b>Code: </b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="code"
                                                       value="${model.code}"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <label><b>Usages: </b></label>
                                            <div class="fg-line">
                                                <input type="number" class="form-control input-sm" name="usages"
                                                       value="${model.usages}"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <label><b>Fullname: </b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="fullname"
                                                       value="${model.fullName}"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <label><b>Username: </b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="username"
                                                       value="${model.username}"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <label><b>Email: </b></label>
                                            <div class="fg-line">
                                                <input type="email" class="form-control input-sm" name="email"
                                                       value="${model.email}"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <label><b>Number Phone: </b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="numberPhone"
                                                       value="${model.numberPhone}"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <label><b>Wallet: </b></label>
                                            <div class="fg-line">
                                                <input type="number" class="form-control input-sm" name="wallet"
                                                       value="${model.wallet}"/>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-6">
                                            <button type="button" class="btn btn-sm btn-success" id="btnSearch">
                                                Tìm Kiếm
                                                <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                            </button>
                                            <button type="button" class="btn btn-sm btn-success" id="btnClear">
                                                Xóa Lựa chọn
                                                <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" value="1" id="page" name="page"/>
                    <input type="hidden" value="${list.pageSize}" id="maxPageItem" name="maxPageItem"/>
                    <input type="hidden" value="" id="sortName" name="sortName"/>
                    <input type="hidden" value="" id="sortBy" name="sortBy"/>
                </form:form>
                <div class="col-xs-12">
                    <div class="table-btn-controls">
                        <div class="pull-right tableTools-container">
                            <div class="dt-buttons btn-overlap btn-group">
                                <a flag="info"
                                   class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                   data-toggle="tooltip"
                                   title="Add Device"
                                   href="<c:url value="/admin/device/edit"/>">
                                    <span>
                                        <i class="fa fa-plus-circle bigger-110 purple"></i>
                                    </span>
                                </a>
                                <button type="button" id="btnDelete"
                                        class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                        data-toggle="tooltip"
                                        title="Turn Off The Device"
                                >
                                <span>
                                    <i class="fa fa-trash-o bigger-110 pink"></i>
                                </span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <%-- button add , delete--%>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <table class="table table-bordered" id="device">
                        <thead>
                            <tr>
                                <th></th>
                                <th>STT</th>
                                <th>Tên Device</th>
                                <th>Code</th>
                                <th>QrCode</th>
                                <th>Usages</th>
                                <th>FullName</th>
                                <th>UserName</th>
                                <th>Email</th>
                                <th>NumberPhone</th>
                                <th>Wallet</th>
                                <th>Status</th>
                                <th>Operation</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${list.list}" varStatus="loop">
                            <tr>
                                <td><input type="checkbox" value="${item.id}" name="" id="checkBox_${item.id}"></td>
                                <td>${loop.index + 1}</td>
                                <td>${item.name}</td>
                                <td>${item.code}</td>
                                <td>
                                    <img src="../../../../template/admin/assets/css/img/alpha.png" alt="">
                                </td>
                                <td>${item.usages}</td>
                                <td>${item.fullName}</td>
                                <td>${item.username}</td>
                                <td>${item.email}</td>
                                <td>${item.numberPhone}</td>
                                <td>${item.wallet}</td>
                                <td>
                                    <c:if test="${item.status == 1}">
                                        <p id="status_${item.code}">Activated</p>
                                    </c:if>
                                    <c:if test="${item.status == 0}">
                                        <p id="status_${item.code}">Not activated</p>
                                    </c:if>
                                </td>
                                <td>
                                    <a class="btn btn-xs btn-primary btn-edit"
                                       data-toggle="tooltip"
                                       title="Update Device"
                                       href="<c:url value="/admin/device/edit?id=${item.id}"/>">
                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                    </a>
                                    <button class="btn btn-xs btn-primary btn-edit"
                                            data-toggle="tooltip"
                                            title="Turn On The Device"
                                            onclick="updateStatus(${item.code})">
                                        <i class="ace-icon fa fa-check bigger-120"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
            <div class="container" style="text-align: right;">
                <ul id="pagination" class="pagination-lg pagination"></ul>
            </div>
            <div class="well text-center" id="content"></div>
        </div>
    </div>
</div>
<div class="modal fade" role="dialog" id="imgmodal">
    <div class="modal-dialog">
        <div class="modal-content"></div>
        <img class="img-responsive" src="" id="show-img">
    </div>
</div>
</div>

<!-- Modal confirm delete -->
<div class="modal fade" id="confirmDeleteBanner" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="warrantyModalLabel">Xác Nhận Thao Tác</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Bạn có chắc chắn muốn xóa các thiết bị đã chọn?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" id="closeModal">Close</button>
                <button type="button" class="btn btn-primary" onclick="confirmDeleteBanner(true)">Save changes
                </button>
            </div>
        </div>
    </div>
</div>

<!-- Modal delete error no item -->
<div class="modal fade" id="errorDelete" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                chưa có banner được chọn để xóa
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

    function loadStatus(code, status){
        var status_ = "status_" + code ;
            if(status == 1){
                $("#" + status_).html("Active");
            }else {
                $("#" + status_).html('Not activated');
            }
    }

    $('#btnSearch').click(function () {
        $('#page').val(1);
        $('#maxPageItem').val(10);
        $('#sortName').val('id');
        $('#sortBy').val('ASC');
        $('#formSubmit').submit();
    });

    $('#btnClear').click(function () {
        $('#page').val(1);
        $('#maxPageItem').val(10);
        $('#sortName').val('id');
        $('#sortBy').val('ASC');
        $("input[name='name']").val('');
        $("input[name='code']").val('');
        $("input[name='usages']").val('');
        $("input[name='fullname']").val('');
        $("input[name='username']").val('');
        $("input[name='email']").val('');
        $("input[name='numberPhone']").val('');
        $("input[name='wallet']").val('');
        $('#formSubmit').submit();
    });


    function showImage(base64File, typeFile) {
        var img = "/images" + base64File;
        $("#show-img").attr('src', img);
        $("#imgmodal").modal('show');
    }

    function updateStatus(code) {
        let url = '${deviceAPIUpdateStatus}' + '/' + code;
        $.ajax({
            url: url,
            type: 'PUT',
            success: function (data) {
                window.location.href = "${deviceSearchURL}?page=1&pageSize=10";
            },
            error: function (data) {
                showError(data);
            }
        });
    }

    $('#btnDelete').click(function () {
        var dataArr = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        if (dataArr.length == 0) {
            $("#errorDelete").modal('show');
            return;
        }
        $("#confirmDeleteBanner").modal("show");
    });

    function confirmDeleteBanner(isSubmit) {
        if (isSubmit) {
            var dataArr = $('tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            var data = [];
            data = dataArr;
            deleteBanner(data);
        }
    }

    function deleteBanner(data) {
        let url = '${deviceAPIDelete}' + '?ids=' + $.each(data, function (index, value) {
            if (index < data.length){
                $("#" + index).append(document.createTextNode(value + ","))
            }else{
                $("#" + index).append(document.createTextNode(value))
            }
        });
        $.ajax({
            url: url,
            type: 'DELETE',
            success: function (data) {
                window.location.href = "${deviceSearchURL}?message=delete_success&page=1&pageSize=10";
            },
            error: function (data) {
                $("#closeModal").click();
                showError(data);
            }
        });
    }

    var totalPage = ${list.totalPage};
    var currentPage = ${list.currentPage};

    if (totalPage == 0) {
        totalPage = 1;
    }

    $(function () {
        $('#pagination').twbsPagination({
            totalPages: totalPage,
            visiblePages: 5,
            startPage: currentPage,
            onPageClick: function (event, page) {
                //$('#content').text('page: ' + page);
                if (currentPage != page) {
                    $('#page').val(page);
                    $('#maxPageItem').val(10);
                    $('#sortName').val('id');
                    $('#sortBy').val('ASC');
                    $('#formSubmit').submit();
                }
            }
        });
    });

</script>
</body>
</html>
