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
<c:url var="priceLevelAPICreate" value="/api/admin/price"/>
<c:url var="priceLevelListURL" value="/admin/pricelevel"/>
<c:url var="priceLevelAPIUpdate" value="/api/admin/price"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Edit Price Level Page</title>
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
                    Danh sách Price Level
                </li>
                <li>
                    Edit Page
                </li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <form class="col-xs-12" id="formSubmit" action="" method="post" modelAttribute="model">
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
                                                <input type="text" class="form-control input-sm" id="name" name="price"
                                                       value="${priceLevelDTO.price}"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-sm-3">
                                                <label><b>TimeFrom: </b></label>
                                            </div>
                                            <div class="col-sm-3">
                                                <input type="text" class="form-control input-sm" id="timeFrom"
                                                       name="timeFrom"
                                                       value="${priceLevelDTO.timeFrom}"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-sm-3">
                                                <label><b>TimeTo: </b></label>
                                            </div>
                                            <div class="col-sm-3">
                                                <input type="text" class="form-control input-sm" id="timeTo"
                                                       name="timeTo"
                                                       value="${priceLevelDTO.timeTo}"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-sm-3">
                                                <label><b>Type: </b></label>
                                            </div>
                                            <div class="col-sm-3">
                                                <select name="type">
                                                    <option value="">--Chọn kiểu thanh toán--</option>
                                                    <option value="MINUTE">MINUTE</option>
                                                    <option value="TRAIL">TRAIL</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <input type="text" value="${priceLevelDTO.id}" hidden id="id"/>

            </form>
            <div class="row text-center btn-addsp">
                <br>
                <c:if test="${empty priceLevelDTO.id}">
                    <button type="button" class="btn btn-success" id="btnAddOrUpdateBuilding">Thêm Mới</button>
                    <button type="button" class="btn btn-danger" id="btnBack">Hủy Bỏ</button>
                </c:if>
                <c:if test="${not empty priceLevelDTO.id}">
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
        var data = {};

        $.each(formData, function (index, v) {
            if (v.name == 'type') {
                data['' + v.name + ''] = v.value;
            } else {
                data['' + v.name + ''] = (Number(v.value));
            }
        });
        debugger;
        if (id == '') {
            createPriceLevel(data);
        } else {
            updatePriceLevel(data, id);
        }
        console.log(data);
    });

    function loadData(id, name) {
        $('#userId').val(id);
        $('#fullName').val(name);
    }

    function createPriceLevel(data) {
        $.ajax({
            url: '${priceLevelAPICreate}',
            data: JSON.stringify(data),
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            success: function (data) {
                window.location.href = "${priceLevelListURL}?page=1&maxPageItem=10";
            },
            error: function (data) {
                showError(data);
            }
        });
    }

    function updatePriceLevel(data, id) {
        let url = '${priceLevelAPIUpdate}' + '/' + id;
        $.ajax({
            url: url,
            data: JSON.stringify(data),
            type: 'PUT',
            contentType: 'application/json',
            dataType: 'json',
            success: function (data) {
                window.location.href = "${priceLevelListURL}?page=1&maxPageItem=10";
                window.location.href = "${priceLevelListURL}?page=1&maxPageItem=10";
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
