<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="modaldemo1" class="modal fade" style="display: none;"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-vertical-center" role="document">
		<div class="modal-content bd-0 tx-14">
			<div class="modal-header pd-y-20 pd-x-25">
				<h6 class="tx-14 mg-b-0 tx-uppercase tx-inverse tx-bold">Поиск подразделений РЖУ</h6>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body pd-25">
				<div class="mg-b-5">
					<div class="card pd-20 pd-sm-40 form-layout form-layout-4">
						<h6 class="card-body-title">Поиск подразделений РЖУ по названию</h6>
						<p class="mg-b-20 mg-sm-b-30">Введите название подразделении.</p>
						<form method="GET" action="${pageContext.request.contextPath}/searchUnderRju">
							<div class="row">
								<label class="col-sm-4 form-control-label">Название: <span
									class="tx-danger">*</span></label>
								<div class="col-sm-8 mg-t-10 mg-sm-t-0">
									<input type="text" name="underrjuname" class="form-control"
										placeholder="Название подразделений">
								</div>
							</div>
							<!-- row -->
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
	</div>
	<!-- modal-dialog -->
</div>