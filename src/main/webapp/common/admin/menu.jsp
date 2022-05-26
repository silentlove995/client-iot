<%--
  Created by IntelliJ IDEA.
  User: dangd
  Date: 2/23/2020
  Time: 12:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.iot.client.utils.SecurityUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="sidebar" class="sidebar                  responsive                    ace-save-state">
    <script type="text/javascript">
        try {
            ace.settings.loadState('sidebar')
        } catch (e) {
        }
    </script>
    <div class="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large">
            <button class="btn btn-success">
                <i class="ace-icon fa fa-signal"></i>
            </button>

            <button class="btn btn-info">
                <i class="ace-icon fa fa-pencil"></i>
            </button>

            <button class="btn btn-warning">
                <i class="ace-icon fa fa-users"></i>
            </button>

            <button class="btn btn-danger">
                <i class="ace-icon fa fa-cogs"></i>
            </button>
        </div>
        <div class="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>

            <span class="btn btn-info"></span>

            <span class="btn btn-warning"></span>

            <span class="btn btn-danger"></span>
        </div>
    </div>
    <ul class="nav nav-list">
        <%--        <c:if test='${SecurityUtils.admin}'>--%>
        <%if (SecurityUtils.getAuthorities().contains("ROLE_ADMIN")) {%>
            <li>
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-desktop"></i>
                    <span class="menu-text"></span>
                    Quản Lý Thiết Bị
                    <b class="arrow fa fa-angle-down"></b>
                </a>
                <b class="arrow"></b>
                <ul class="submenu">
                    <li>
                        <a href='<c:url value="/admin/device"/>'>
                            <i class="menu-icon fa fa-caret-right"></i>
                            Danh Sách Thiết Bị
                        </a>
                        <b class="arrow"></b>
                    </li>
                </ul>

            </li>
            <li>
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-desktop"></i>
                    <span class="menu-text"></span>
                    Quản lý Mức Giá
                    <b class="arrow fa fa-angle-down"></b>
                </a>
                <b class="arrow"></b>
                <ul class="submenu">
                    <li>
                        <a href='<c:url value="/admin/pricelevel"/>'>
                            <i class="menu-icon fa fa-caret-right"></i>
                            Danh Sách Mức Giá
                        </a>
                        <b class="arrow"></b>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-desktop"></i>
                    <span class="menu-text"></span>
                    Quản Lý Thẻ Nạp
                    <b class="arrow fa fa-angle-down"></b>
                </a>
                <b class="arrow"></b>
                <ul class="submenu">
                    <li>
                        <a href='<c:url value="/admin/card"/>'>
                            <i class="menu-icon fa fa-caret-right"></i>
                            Danh Sách Thẻ Nạp
                        </a>
                        <b class="arrow"></b>
                    </li>
                </ul>
            </li>

            <li>
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-desktop"></i>
                    <span class="menu-text"></span>
                    Lịch sử
                    <b class="arrow fa fa-angle-down"></b>
                </a>
                <b class="arrow"></b>
                <ul class="submenu">
                    <li>
                        <a href='<c:url value="/admin/history/device"/>'>
                            <i class="menu-icon fa fa-caret-right"></i>
                            Thiết bị
                        </a>
                        <b class="arrow"></b>
                    </li>
                </ul>
                <ul class="submenu">
                    <li>
                        <a href='<c:url value="/admin/history/payment"/>'>
                            <i class="menu-icon fa fa-caret-right"></i>
                            Thanh toán
                        </a>
                        <b class="arrow"></b>
                    </li>
                </ul>
            </li>
        <%}%>

        <%if (SecurityUtils.getAuthorities().contains("ROLE_USER")) {%>
            <li>
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-tag"></i>
                    <span class="menu-text"></span>
                    WebCam
                    <b class="arrow fa fa-angle-down"></b>
                </a>
                <b class="arrow"></b>
                <ul class="submenu">
                    <li>
                        <a href='<c:url value="/user/webcam"/>'>
                            <i class="menu-icon fa fa-caret-right"></i>
                            Sử Dụng WebCam
                        </a>
                        <b class="arrow"></b>
                    </li>
                </ul>
            </li>

            <li>
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-tag"></i>
                    <span class="menu-text"></span>
                    Card
                    <b class="arrow fa fa-angle-down"></b>
                </a>
                <b class="arrow"></b>
                <ul class="submenu">
                    <li>
                        <a href='<c:url value="/user/card"/>'>
                            <i class="menu-icon fa fa-caret-right"></i>
                            Nạp thẻ
                        </a>
                        <b class="arrow"></b>
                    </li>
                </ul>
            </li>
        <%}%>
        <li>
            <%--<a href="#" class="dropdown-toggle">
                <i class="menu-icon fa fa-list-alt"></i>
                <span class="menu-text"></span>
                Quản Lý Bảo Hành
                <b class="arrow fa fa-angle-down"></b>
            </a>
            <b class="arrow"></b>
            <ul class="submenu">
                <li>
                    <a href='<c:url value="/admin/warranty/list"/>'>
                        <i class="menu-icon fa fa-caret-right"></i>
                        Hình Ảnh Thông Tin Bảo Hành
                    </a>
                    <b class="arrow"></b>
                </li>
            </ul>--%>
        </li>
        <li>
            <%--<a href="#" class="dropdown-toggle">
                <i class="menu-icon fa fa-leaf green"></i>
                <span class="menu-text"></span>
                Quản Lý Logo
                <b class="arrow fa fa-angle-down"></b>
            </a>
            <b class="arrow"></b>
            <ul class="submenu">
                <li>
                    <a href='<c:url value="/admin/logo/list"/>'>
                        <i class="menu-icon fa fa-caret-right"></i>
                        Hình Ảnh Logo công ty
                    </a>
                    <b class="arrow"></b>
                </li>
            </ul>--%>
        </li>
        <li>
            <%--<a href="#" class="dropdown-toggle">
                <i class="menu-icon fa fa-pencil-square-o"></i>
                <span class="menu-text"></span>
                Quản Lý Nhà Sản Xuất
                <b class="arrow fa fa-angle-down"></b>
            </a>
            <b class="arrow"></b>
            <ul class="submenu">
                <li>
                    <a href='<c:url value="/admin/manufacturer/list?page=1&maxPageItem=10"/>'>
                        <i class="menu-icon fa fa-caret-right"></i>
                        Danh Sách Nhà Sản Xuất
                    </a>
                    <b class="arrow"></b>
                </li>
            </ul>--%>
        </li>
        <li>
            <%--            <a href="#" class="dropdown-toggle">--%>
            <%--                <i class="menu-icon fa fa-tachometer"></i>--%>
            <%--                <span class="menu-text"></span>--%>
            <%--                Quản Lý bài viết--%>
            <%--                <b class="arrow fa fa-angle-down"></b>--%>
            <%--            </a>--%>
            <%--            <b class="arrow"></b>--%>
            <%--            <ul class="submenu">--%>
            <%--                <li>--%>
            <%--                    <a href='<c:url value="/admin/news/list?page=1&maxPageItem=10&code=TINTUC"/>'>--%>
            <%--                        <i class="menu-icon fa fa-caret-right"></i>--%>
            <%--                        Danh Sách bài viết tin tức--%>
            <%--                    </a>--%>
            <%--                    <b class="arrow"></b>--%>
            <%--                </li>--%>
            <%--                <li>--%>
            <%--                    <a href='<c:url value="/admin/news/list?page=1&maxPageItem=10&code=GIOITHIEU"/>'>--%>
            <%--                        <i class="menu-icon fa fa-caret-right"></i>--%>
            <%--                        Danh Sách bài viết giới thiệu--%>
            <%--                    </a>--%>
            <%--                    <b class="arrow"></b>--%>
            <%--                </li>--%>
            <%--                <li>--%>
            <%--                    <a href='<c:url value="/admin/news/list?page=1&maxPageItem=10&code=CONGTRINHTIEUBIEU"/>'>--%>
            <%--                        <i class="menu-icon fa fa-caret-right"></i>--%>
            <%--                        Danh Sách bài viết công trình tiêu biểu--%>
            <%--                    </a>--%>
            <%--                    <b class="arrow"></b>--%>
            <%--                </li>--%>
            <%--            </ul>--%>
        </li>
    </ul>
    <div class="sidebar-toggle sidebar-collapse">
        <i class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left"
           data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>
</div>