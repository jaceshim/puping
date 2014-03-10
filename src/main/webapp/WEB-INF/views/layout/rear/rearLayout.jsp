<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
	<title>Puping Admin ::: <decorator:title /></title>

	<!--=== CSS ===-->

	<!-- Bootstrap -->
	<link href="/static/melon/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />

	<!-- jQuery UI -->
	<!--<link href="/static/melon/plugins/jquery-ui/jquery-ui-1.10.2.custom.css" rel="stylesheet" type="text/css" />-->
	<!--[if lt IE 9]>
		<link rel="stylesheet" type="text/css" href="/static/melon/plugins/jquery-ui/jquery.ui.1.10.2.ie.css"/>
	<![endif]-->

	<!-- Theme -->
	<link href="/static/melon/assets/css/main.css" rel="stylesheet" type="text/css" />
	<link href="/static/melon/assets/css/plugins.css" rel="stylesheet" type="text/css" />
	<link href="/static/melon/assets/css/responsive.css" rel="stylesheet" type="text/css" />
	<link href="/static/melon/assets/css/icons.css" rel="stylesheet" type="text/css" />

	<link rel="stylesheet" href="/static/melon/assets/css/fontawesome/font-awesome.min.css">
	<!--[if IE 7]>
		<link rel="stylesheet" href="/static/melon/assets/css/fontawesome/font-awesome-ie7.min.css">
	<![endif]-->

	<!--[if IE 8]>
		<link href="/static/melon/assets/css/ie8.css" rel="stylesheet" type="text/css" />
	<![endif]-->
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600,700' rel='stylesheet' type='text/css'>

	<link href="/static/melon/assets/css/puping.css" rel="stylesheet" type="text/css" />

	<!--=== JavaScript ===-->

	<script type="text/javascript" src="/static/melon/assets/js/libs/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="/static/melon/plugins/jquery-ui/jquery-ui-1.10.2.custom.min.js"></script>

	<script type="text/javascript" src="/static/melon/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/static/melon/assets/js/libs/lodash.compat.min.js"></script>

	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
		<script src="/static/melon/assets/js/libs/html5shiv.js"></script>
	<![endif]-->

	<!-- Smartphone Touch Events -->
	<script type="text/javascript" src="/static/melon/plugins/touchpunch/jquery.ui.touch-punch.min.js"></script>
	<script type="text/javascript" src="/static/melon/plugins/event.swipe/jquery.event.move.js"></script>
	<script type="text/javascript" src="/static/melon/plugins/event.swipe/jquery.event.swipe.js"></script>

	<!-- General -->
	<script type="text/javascript" src="/static/melon/assets/js/libs/breakpoints.js"></script>
	<script type="text/javascript" src="/static/melon/plugins/respond/respond.min.js"></script> <!-- Polyfill for min/max-width CSS3 Media Queries (only for IE8) -->
	<script type="text/javascript" src="/static/melon/plugins/cookie/jquery.cookie.min.js"></script>
	<script type="text/javascript" src="/static/melon/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script type="text/javascript" src="/static/melon/plugins/slimscroll/jquery.slimscroll.horizontal.min.js"></script>

	<!-- Page specific plugins -->
	<!-- Charts -->
	<!--[if lt IE 9]>
		<script type="text/javascript" src="/static/melon/plugins/flot/excanvas.min.js"></script>
	<![endif]-->
	<script type="text/javascript" src="/static/melon/plugins/sparkline/jquery.sparkline.min.js"></script>
	<script type="text/javascript" src="/static/melon/plugins/flot/jquery.flot.min.js"></script>
	<script type="text/javascript" src="/static/melon/plugins/flot/jquery.flot.tooltip.min.js"></script>
	<script type="text/javascript" src="/static/melon/plugins/flot/jquery.flot.resize.min.js"></script>
	<script type="text/javascript" src="/static/melon/plugins/flot/jquery.flot.time.min.js"></script>
	<script type="text/javascript" src="/static/melon/plugins/flot/jquery.flot.growraf.min.js"></script>
	<script type="text/javascript" src="/static/melon/plugins/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>

	<script type="text/javascript" src="/static/melon/plugins/daterangepicker/moment.min.js"></script>
	<script type="text/javascript" src="/static/melon/plugins/daterangepicker/daterangepicker.js"></script>
	<script type="text/javascript" src="/static/melon/plugins/blockui/jquery.blockUI.min.js"></script>

	<script type="text/javascript" src="/static/melon/plugins/fullcalendar/fullcalendar.min.js"></script>

	<!-- Noty -->
	<script type="text/javascript" src="/static/melon/plugins/noty/jquery.noty.js"></script>
	<script type="text/javascript" src="/static/melon/plugins/noty/layouts/top.js"></script>
	<script type="text/javascript" src="/static/melon/plugins/noty/themes/default.js"></script>

	<!-- Forms -->
	<script type="text/javascript" src="/static/melon/plugins/uniform/jquery.uniform.min.js"></script>
	<script type="text/javascript" src="/static/melon/plugins/select2/select2.min.js"></script>

	<!-- App -->
	<script type="text/javascript" src="/static/melon/assets/js/app.js"></script>
	<script type="text/javascript" src="/static/melon/assets/js/plugins.js"></script>
	<script type="text/javascript" src="/static/melon/assets/js/plugins.form-components.js"></script>

	<script>
	$(document).ready(function(){
		"use strict";
		App.init(); // Init layout and core plugins
	});
	</script>
	
	<decorator:head />
</head>

<body>

	<!-- Header -->
	<header class="header navbar navbar-fixed-top" role="banner">
		<!-- Top Navigation Bar -->
		<div class="container">

			<!-- Only visible on smartphones, menu toggle -->
			<ul class="nav navbar-nav">
				<li class="nav-toggle"><a href="javascript:void(0);" title=""><i class="icon-reorder"></i></a></li>
			</ul>

			<!-- Logo -->
			<a class="navbar-brand" href="index.html">
				<strong>Puping</strong>
				Admin
			</a>
			<!-- /logo -->

			<!-- Sidebar Toggler -->
 			<a href="#" class="toggle-sidebar bs-tooltip" data-placement="bottom" data-original-title="Toggle navigation"> <i class="icon-reorder"></i> </a>
			<!-- /Sidebar Toggler -->

			<!-- Top Right Menu -->
			<ul class="nav navbar-nav navbar-right">
				<!-- User Login Dropdown -->
				<li class="dropdown user">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<span class="username"> John Doe</span>
						<i class="icon-caret-down small"></i>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#"><i class="icon-user"></i> 정보변경</a></li>
						<li class="divider"></li>
						<li><a href="login.html"><i class="icon-key"></i> 로그아웃</a></li>
					</ul>
				</li>
				<!-- /user login dropdown -->
			</ul>
			<!-- /Top Right Menu -->
		</div>
		<!-- /top navigation bar -->

	</header> <!-- /.header -->

	<div id="container">
		<div id="sidebar" class="sidebar-fixed">
			<div id="sidebar-content">
				<!--=== Navigation ===-->
				<ul id="nav">
					<li class="current">
						<a href="index.html">
							<i class="icon-dashboard"></i>
							Dashboard
						</a>
					</li>
					<li>
						<a href="charts.html">
							<i class="icon-gift"></i>
							상품관리
						</a>
					</li>					
					<li>
						<a href="javascript:void(0);">
							<i class="icon-sitemap"></i>
							카테고리 관리
							<span class="label label-info pull-right"></span>
						</a>
						<ul class="sub-menu">
							<li>
								<a href="/rear/category/list">
								<i class="icon-angle-right"></i>
								카테고리 목록
								</a>
							</li>
							<li>
								<a href="/rear/category/tag/list">
								<i class="icon-angle-right"></i>
								TAG 목록
								</a>
							</li>
							<li>
								<a href="/rear/category/tag/unreg/list">
								<i class="icon-angle-right"></i>
								미등록 TAG 목록
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="/rear/member/list">
							<i class="icon-user"></i>
							회원관리
						</a>
					</li>
					<li>
						<a href="/rear/apps/version">
							<i class="icon-leaf"></i>
							어플버전 관리
						</a>
					</li>
					<li>
						<a href="/rear/notice/list">
							<i class="icon-bullhorn"></i>
							공지사항 관리
						</a>
					</li>					
					<li>
						<a href="/rear/manager/list">
							<i class="icon-user-md"></i>
							 관리자 관리
						</a>
					</li>
				</ul>
			</div>
			<div id="divider" class="resizeable"></div>
		</div>
		<!-- /Sidebar -->

		<div id="content">
			<div class="container">
				<!-- Breadcrumbs line -->
				<div class="crumbs">
					<ul id="breadcrumbs" class="breadcrumb">
						<li>
							<i class="icon-home"></i>
							<a href="index.html">Dashboard</a>
						</li>
						<li class="current">
							<a href="pages_calendar.html" title="">Calendar</a>
						</li>
					</ul>
				</div>
				<!-- /Breadcrumbs line -->
				
				<div class="row">
					<div id="dev-content-wrapper" class="col-md-12" style="margin-top: 10px;">
						<!-- /Content -->
						<decorator:body />
						<!-- /Content -->					
					</div>
				</div>
			</div>
			<!-- /.container -->

		</div>
	</div>

</body>
</html>
<!-- Localized -->
