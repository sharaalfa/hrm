<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Аналитика по переписке</title>
 
<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">HRM - технологий и аналитики</a>
	</div>
  </div>
</nav>
 
<div class="jumbotron">
  <div class="container">
	<h1>${title}</h1>
	<p>
		<c:if test="${not empty name}">
			Hello ${name}
		</c:if>
 
		<c:if test="${empty name}">
			Аналитика по переписке
		</c:if>
    </p>
    <p>
		<a class="btn btn-primary btn-lg" href="/main" role="button">Построить графики</a>
	</p>
	</div>
</div>
 
<div class="container">
 
  <div class="row">
	<div class="col-md-4">
		<h2>Общий график</h2>
		<p>По центральности</p>
		<p>
			<a class="btn btn-default" href="/chart" role="button">View details</a>
		</p>
	</div>
	<div class="col-md-4">
		<h2>Таблица первых десяти значимых</h2>
		<p>По центральности</p>
		<p>
			<a class="btn btn-default" href="/chartMax" role="button">View details</a>
		</p>
	</div>
	<div class="col-md-4">
		<h2>Подготовка данных</h2>
		<p>Загрузка, парсинг, анализ данных</p>
		<p>
			<a class="btn btn-default" href="/analyzePageHTML" role="button">View details</a>
		</p>
	</div>
  </div>
	<table class="table">
		<tr>

			<div>
				<td><a> </a></td>
			</div>

	</table>
	<table class="table">
		<tr>

			<div>
				<td><a> </a></td>
			</div>

	</table>
	<table class="table">
		<tr>

			<div>
				<td><a> </a></td>
			</div>

	</table>
	<div class="container">
		<div class="col-md-10">
			<h4>Центральность по посредничеству</h4>
			<p>Мера важности подразделения в коммуникациях других поразделений</p>
		</div>
	</div>


	<table class="table">
		<tr>

			<div>
				<td><a> </a></td>
			</div>

	</table>
	<table class="table">
		<tr>

			<div>
				<td><a> </a></td>
			</div>

	</table>
	<div class="container">
		<div class="col-md-10">
			<h4>Центральность по левому собственному вектору</h4>
			<h5>Степень престижа, источник активности</h5>
			<p>Степень важности данного подразделения, которая пропорциональна степени важности подразделений,
				с которым он связан</p>
		</div>
	</div>
	<table class="table">
		<tr>

			<div>
				<td><a> </a></td>
			</div>

	</table>

	<table class="table">
		<tr>

			<div>
				<td><a> </a></td>
			</div>

	</table>
	<div class="container">
		<div class="col-md-10">
			<h4>Центральность по правому собственному вектору</h4>
			<h5>Количество участия, центральность участия</h5>
			<p>Степень важности данного подразделения, которая пропорциональна степени важности подразделений,
				с которым он связан</p>
		</div>
	</div>
	<table class="table">
		<tr>

			<div>
				<td><a> </a></td>
			</div>

	</table>
	<table class="table">
		<tr>

			<div>
				<td><a> </a></td>
			</div>

	</table>
	<table class="table">
	<tr>

		<div>
			<td><a> </a></td>
		</div>

    </table>

	<table class="table">


	 <div>
		 <td>
			 <p>&copy; HRM - технологий и аналитики 2017</p>
		 </td>
	 </div>
 </table>
	<style>
		table {
			width: 300px; /* Ширина таблицы */
			margin: auto; /* Выравниваем таблицу по центру окна  */

		}
		td {
			text-align: center;
		}
	</style>

</div>
 
<spring:url value="/resources/core/css/hello.js" var="coreJs" />
<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs" />
 
<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>