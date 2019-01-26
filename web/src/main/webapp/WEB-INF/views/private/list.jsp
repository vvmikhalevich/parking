<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h4 class="header" align="center">Private</h4>



<body>



	<div class="row">
		<div class="col col-sm-12">

			<div class="row">
				<div class="col col-sm-3">
					<div class="leftmenu hidden-xs">
						<div class="selecteditem">
							<a class="left-menu-href" href="${pagesPrivate}">Профиль</a>
						</div>

						<br />
						<div class="menuitem">
							<a class="" href="${pagesTransaction}">История расходов</a>
						</div>

						<br />
						<div class="menuitem">
							<a class="" href="${pagesEvent}">История событий</a>
						</div>



					</div>


				</div>
			</div>
		</div>

		<div class="col col-sm-9">
			<div class="row">
				<div class="col col-sm-12">
					<div class="account-content-section">
						<table>
							<tbody>
								<tr>
									<td class="account-field-caption">Имя:</td>
									<td><sec:authentication property="name" /></td>
								</tr>
								<tr>
									<td class="account-field-caption">E-mail:</td>
									<td>v.v.mikhalevich@gmail.com</td>
								</tr>
								<tr>
									<td class="account-field-caption">Последний визит:</td>
									<td>13:26:08 26 января 2019</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>




			<div class="row">
				<div class="col col-sm-12">
					<div class="account-content-section">
						<div class="account-field-caption">Последние события:</div>
						<table width="100%">
							<tbody>
								<tr id="theader-event_logs"
									style="font-size: 85%; text-align: center;">
									<td id="event_logs_d___created_at">Время</td>
									<td id="event_logs_____msg">Событие</td>
								</tr>
							</tbody>
							<tbody id="dyndata-event_logs">
								<tr class="listline">
									<td class="">2019-01-27 01:20:58</td>
									<td class="">Успешный вход (IP=37.212.69.21)</td>
								</tr>
								<tr class="listline">
									<td class="">2019-01-26 13:26:08</td>
									<td class="">Успешный вход (IP=37.212.69.21)</td>
								</tr>
								<tr class="listline">
									<td class="">2019-01-25 20:31:30</td>
									<td class="">Успешный вход (IP=86.57.167.170)</td>
								</tr>
								<tr class="listline">
									<td class="">2019-01-24 23:57:16</td>
									<td class="">Успешный вход (IP=37.212.68.149)</td>
								</tr>
								<tr class="listline">
									<td class="">2019-01-24 21:39:27</td>
									<td class="">Успешный вход (IP=37.212.68.149)</td>
								</tr>
								<tr class="listline">
									<td class="">2019-01-24 21:39:24</td>
									<td class="">Email-адрес успешно подтверждён
										(IP=37.212.68.149)</td>
								</tr>
								<tr class="listline">
									<td class="">2019-01-24 21:39:07</td>
									<td class="">Пользователь зарегистрирован
										(IP=37.212.68.149)</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

