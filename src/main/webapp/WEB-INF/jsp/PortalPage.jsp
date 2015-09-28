<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:page>

	<div class="page-header" style="padding-left: 15px; margin: 0;">

		<div class="row">

			<div class="col-lg-2">
				<img src="/resources/public/images/logo.png">
			</div>

			<div class="col-lg-10">
				<h1>Scheduler Micro Service</h1>
			</div>

		</div>

	</div>

	<div class="portal-page-container"></div>

	<script type="text/javascript">

		require( [ 'jquery', 'js/components/base-view/user-base-page-view', 'js/pages/portal-page/portal-page', 'translator' ], function ( $, Page, portal, Translator ) {

			var t = new Translator( {
				title: 'Portal page'
			} );

			var breadcrumbs = [
				{ link: '#', title: t.title }
			];

			var pageView = new Page( {
				el: $( '.portal-page-container' ),
				bodyRenderer: portal,
				breadcrumbs: breadcrumbs,
				options: {}
			} );
			pageView.render();
		} );
	</script>

</tags:page>