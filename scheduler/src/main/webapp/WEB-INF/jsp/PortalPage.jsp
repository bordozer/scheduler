<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:page>

	<div class="portal-page-container"></div>

	<script type="text/javascript">

		require( [ 'jquery', 'js/components/base-view/user-base-page-view', 'js/pages/portal-page/portal-page', 'translator' ], function ( $, Page, render, Translator ) {

			var t = new Translator( {
				title: 'Portal page'
			} );

			var breadcrumbs = [
				{ link: '#', title: t.title }
			];

			var pageView = new Page( {
				el: $( '.portal-page-container' ),
				bodyRenderer: render,
				breadcrumbs: breadcrumbs,
				options: {}
			} );
			pageView.render();
		} );
	</script>

</tags:page>