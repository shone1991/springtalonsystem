<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="modaldemo1" class="modal fade" style="display: none;"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-vertical-center" role="document">
		<div class="modal-content bd-0 tx-14">
			<div class="modal-header pd-y-20 pd-x-25">
				<h6 class="tx-14 mg-b-0 tx-uppercase tx-inverse tx-bold">Поиск
					пользователя</h6>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body pd-25">

					<div class="form-layout">
						<form method="GET"
							action="${pageContext.request.contextPath}/searchUser">
							<div class="row mg-b-25">
								<div class="col-lg-12">
									<div class="form-group">
										<label class="form-control-label">Имя: <span
											class="tx-danger">*</span></label> <input type="text" name="name"
											class="form-control" placeholder="Имя">
									</div>
									<!-- row -->
								</div>
								<div class="col-lg-12">
									<div class="form-group">
										<label class="form-control-label">Фамилия: <span
											class="tx-danger">*</span></label> <input type="text" name="surname"
											class="form-control" placeholder="Фамилия">

									</div>
								</div>
								<!-- row -->
								<div class="col-lg-12">
									<div class="form-group">
										<label class="form-control-label">Имя пользователя: <span
											class="tx-danger">*</span>
										</label> <input type="text" name="nickname" class="form-control"
											placeholder="Имя пользователя">
									</div>
									<!-- row -->
								</div>
								<div class="col-lg-12">
									<div class="form-group">
										<label class="form-control-label">Место работы: <span
											class="tx-danger">*</span>
										</label> <input type="text" name="workat" id="workat"
											class="form-control" placeholder="Место работы">

									</div>
									<!-- row -->
								</div>
							</div>
							<div class="form-layout-footer mg-t-30">
								<input type="submit" value="Поиск" class="btn btn-info mg-r-5">
								<button class="btn btn-secondary" data-dismiss="modal">Отмена</button>
							</div>
							<!-- form-layout-footer -->

						</form>
					</div>
					<!-- card -->

			</div>
		</div>
	</div>
	<!-- modal-dialog -->
</div>