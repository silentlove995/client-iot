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
<c:url var="deviceSearchURL" value="/admin/historydevice"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Lịch Sử Thiết Bị</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Lịch Sử Thiết Bị</a>
                </li>
                <li>
                    Lịch Sử Thiết Bị
                </li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <form:form class="col-xs-12" action="" id="formSubmit" method="get"
                          modelAttribute="model" >
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
                                            <label><b>UserName: </b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="username"
                                                       value=""/>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <label><b>Email: </b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="email"
                                                       value=""/>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <label><b>NumberPhone: </b></label>
                                            <div class="fg-line">
                                                <input type="date" class="form-control input-sm" name="numberPhone"
                                                       value=""/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <label><b>CreateDate From: </b></label>
                                            <div class="fg-line">
                                                <input type="date" class="form-control input-sm" name="createdDateFrom"
                                                       value=""/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <label><b>CreateDate To: </b></label>
                                            <div class="fg-line">
                                                <input type="date" class="form-control input-sm" name="createdDateTo"
                                                       value=""/>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <label><b>PaymentType: </b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="paymentType"
                                                       value=""/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <label><b>Total From: </b></label>
                                            <div class="fg-line">
                                                <input type="number" class="form-control input-sm" name="totalFrom"
                                                       value=""/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <label><b>Total To: </b></label>
                                            <div class="fg-line">
                                                <input type="number" class="form-control input-sm" name="totalTo"
                                                       value=""/>
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
                </form:form>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <table class="table table-bordered" id="device">
                        <thead>
                        <tr>
                            <th>STT</th>
                            <th>Fullname</th>
                            <th>Username</th>
                            <th>NumberPhone</th>
                            <th>Email</th>
                            <th>PaymentType</th>
                            <th>Total</th>
                            <th>CreateDate</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${list.list}" varStatus="loop">
                            <tr>
                                <td>${loop.index + 1}</td>
                                <td>${item.fullName}</td>
                                <td>${item.username}</td>
                                <td>${item.numberphone}</td>
                                <td>${item.email}</td>
                                <td>${item.paymenttype}</td>
                                <td>${item.total}</td>
                                <td>${item.createdate}</td>
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
<script type="text/javascript">


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
