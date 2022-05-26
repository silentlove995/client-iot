<%--
  Created by IntelliJ IDEA.
  User: dangd
  Date: 2/23/2020
  Time: 2:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><dec:title default="Trang chủ Admin"/></title>
    <link rel="icon" href="<c:url value='/template/image/logo.ico' />" type="image/gif" sizes="16x16">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="/webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/template/admin/css/site.css' />"/>
    <link rel="stylesheet" href="<c:url value='/template/admin/css/richtext.min.css' />">
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/bootstrap.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='/template/admin/font-awesome/4.5.0/css/font-awesome.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/ace.min.css' />" class="ace-main-stylesheet"
          id="main-ace-style"/>
    <script src="<c:url value='/template/admin/assets/js/ace-extra.min.js' />"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type='text/javascript' src='<c:url value="/template/admin/js/jquery-2.2.3.min.js" />'></script>
    <script src="<c:url value='/template/admin/assets/js/jquery.2.1.1.min.js' />"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <%--<script src="<c:url value='/template/paging/jquery.twbsPagination.js' />"></script>--%>

    <%--<script src="<c:url value='/ckeditor/ckeditor.js' />"></script>--%>
    <%-- jquery twb --%>
    <script src="<c:url value='/template/admin/js/paging/jquery.twbsPagination.js' />"></script>
    <script src="<c:url value="/template/admin/js/richtext/jquery.richtext.js" />"></script>
    <script src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"></script>
    <script src="<c:url value='/template/webjars_lib/sockjs.min.js' />"></script>
    <script src="<c:url value='/template/webjars_lib/stomp.min.js' />"></script>
<%--    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>--%>
</head>
<body class="no-skin">
<!-- header -->
<%@ include file="/common/admin/header.jsp" %>
<!-- header -->

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>
    <!-- header -->
    <%@ include file="/common/admin/menu.jsp" %>
    <!-- header -->

    <dec:body/>

    <!-- footer -->
    <%@ include file="/common/admin/footer.jsp" %>
    <!-- footer -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse display">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div>


<script src="<c:url value='/template/admin/assets/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery-ui.custom.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.ui.touch-punch.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.easypiechart.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.sparkline.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.flot.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.flot.pie.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.flot.resize.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/ace-elements.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/ace.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/bootstrap.min.js'/>"></script>

<%--modal show error--%>
<div class="modal fade" role="dialog" id="modalShowError">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="alert alert-block alert-danger">
                    <i class="ace-icon fa fa-exclamation-triangle red bigger-150"></i>
                    <h4 id="errorMessage"></h4>
                </div>
            </div>
            <div class="modal-footer justify-content-center">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">OK</button>
            </div>
        </div>
    </div>
</div>

<!-- page specific plugin scripts -->
<script src="<c:url value='/template/admin/assets/js/jquery-ui.min.js'/>"></script>
<script>

    $(document).ready(connect(), connectDevice());

    function showError(data) {
        var message = JSON.parse(data.responseText).message.message;
        $("#errorMessage").html(message);
        $("#modalShowError").modal('show');
    }

    /*function setConnected(connected) {
        $("#connect").prop("disabled", connected);
        $("#disconnect").prop("disabled", !connected);
        if (connected) {
            $("#conversation").show();
        }
        else {
            $("#conversation").hide();
        }
        $("#greetings").html("");
    }*/

    /*function connect() {
        const client = Stomp.over('ws://localhost:9092/ws');
        client.connect();
        client.subscribe("/topic/demo", function (message) {
            console.log(message.body);
        })
    }*/

    let host =  + "ws://112.213.87.159:9092/ws" + "";
    function connect() {
        const socket = new WebSocket("ws://112.213.87.159:9092/ws");
        const client = Stomp.over(socket);
        client.connect({},
            () => {
                client.subscribe('/topic/demo', (data) => {
                    const body = JSON.parse(data.body);
                    console.log(body);
                    loadStatus(body.code, body.status);
                });
            },
            (e) => {
                console.log(e);

            }
        );
    }
    function connectDevice() {
        const socket = new WebSocket("ws://112.213.87.159:9092/ws");
        const client = Stomp.over(socket);
        client.connect({},
            () => {
                client.subscribe('/topic/device/web', (data) => {
                    const body = JSON.parse(data.body);
                    console.log(body);
                    loadStatus(body.code, body.status);
                });
            },
            (e) => {
                console.log(e);
            }
        );
    }

</script>
</body>
</html>