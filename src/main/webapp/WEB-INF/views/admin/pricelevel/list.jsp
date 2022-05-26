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
<c:url var="pricelevelAPIDelete" value="/api/admin/price"/>
<c:url var="pricelevelSearchURL" value="/admin/pricelevel"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Danh Sách Mức Giá</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Danh Sách Mức Giá</a>
                </li>
                <li>
                    Danh Sách Mức Giá
                </li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <form:form class="col-xs-12" action="${pricelevelSearchURL}" id="formSubmit" method="get"
                          >
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
                                            <label><b>Giá: </b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="price"
                                                       />
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
                                   title="Add PriceLevel"
                                   href="<c:url value="/admin/pricelevel/edit"/>">
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
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th></th>
                                <th>STT</th>
                                <th>Price</th>
                                <th>TimeFrom</th>
                                <th>TimeTo</th>
                                <th>Type</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${list.list}" varStatus="loop">
                            <tr>
                                <td><input type="checkbox" value="${item.id}" name="" id="checkBox_${item.id}"></td>
                                <td>${loop.index + 1}</td>
                                <td>${item.price}</td>
                                <td>${item.timeFrom}</td>
                                <td>${item.timeTo}</td>
                                <td>${item.type}</td>
                                <td>
                                    <a class="btn btn-xs btn-primary btn-edit"
                                       data-toggle="tooltip"
                                       title="Update Device"
                                       href="<c:url value="/admin/pricelevel/edit?id=${item.id}"/>">
                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                    </a>
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
                Bạn có chắc chắn muốn xóa các giá đã chọn?
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
                Chưa có giá được chọn để xóa
            </div>
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
        $("input[name='price']").val('');
        $('#formSubmit').submit();
    });


    function showImage(base64File, typeFile) {
        var img = "/images" + base64File;
        $("#show-img").attr('src', img);
        $("#imgmodal").modal('show');
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
        let url = '${pricelevelAPIDelete}' + '?ids=' + $.each(data, function (index, value) {
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
                window.location.href = "${pricelevelSearchURL}?message=delete_success&page=1&pageSize=10";
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
