<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
<style>
    .overlay_info {border-radius: 6px; margin-bottom: 12px; float:left;position: relative; border: 1px solid #ccc; border-bottom: 2px solid #ddd;background-color:#fff;}
    .overlay_info:nth-of-type(n) {border:0; box-shadow: 0px 1px 2px #888;}
    .overlay_info a {display: block; background: #d95050; background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center; text-decoration: none; color: #fff; padding:12px 36px 12px 14px; font-size: 14px; border-radius: 6px 6px 0 0}
    .overlay_info a strong {background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/place_icon.png) no-repeat; padding-left: 27px;}
    .overlay_info .desc {padding:14px;position: relative; min-width: 190px; height: 56px}
    .overlay_info img {vertical-align: top;}
    .overlay_info .address {font-size: 12px; color: #333; position: absolute; left: 80px; right: 14px; top: 24px; white-space: normal}
    .overlay_info:after {content:'';position: absolute; margin-left: -11px; left: 50%; bottom: -12px; width: 22px; height: 12px; background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png) no-repeat 0 bottom;}
</style>
</head>
<body>
<div id="map" style="width:100%;height:350px"></div> <!-- 지도를 표시할 div 입니다 -->
<div id="roadview" style="width:100%;height:300px"></div> <!-- 로드뷰를 표시할 div 입니다 -->

 <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8a6b0cdd857fc26bd76d685a05a24159"></script>
	
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8a6b0cdd857fc26bd76d685a05a24159"></script>
<script>
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapCenter = new kakao.maps.LatLng(${lat}, ${lng}), // 지도의 중심좌표
	    mapOption = {
	        center: mapCenter, // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };
	
	var map = new kakao.maps.Map(mapContainer, mapOption);
	
	// 커스텀 오버레이에 표시할 내용입니다
	// HTML 문자열 또는 Dom Element 입니다
	var content = '<div class="overlay_info">';
	content += '    <a href="https://place.map.kakao.com/17600274" target="_blank"><strong>월정리 해수욕장</strong></a>';
	content += '    <div class="desc">';
	content += '        <img src="${img}" alt="${description}">';
	content += '        <span class="address">제주특별자치도 제주시 구좌읍 월정리 33-3</span>';
	content += '    </div>';
	content += '</div>';
	
	// 커스텀 오버레이가 표시될 위치입니다 
	var position = new kakao.maps.LatLng(${lat}, ${lng});
	
	// 커스텀 오버레이를 생성합니다
	var mapCustomOverlay = new kakao.maps.CustomOverlay({
	    position: position,
	    content: content,
	    xAnchor: 0.5, // 커스텀 오버레이의 x축 위치입니다. 1에 가까울수록 왼쪽에 위치합니다. 기본값은 0.5 입니다
	    yAnchor: 1.1 // 커스텀 오버레이의 y축 위치입니다. 1에 가까울수록 위쪽에 위치합니다. 기본값은 0.5 입니다
	});
	
	//rvCustomOverlay.setAltitude(2); //커스텀 오버레이의 고도값을 설정합니다.(로드뷰 화면 중앙이 0입니다)
	rvCustomOverlay.setMap(rv);
	
	var projection = rv.getProjection(); // viewpoint(화면좌표)값을 추출할 수 있는 projection 객체를 가져옵니다.
	
	// 커스텀오버레이의 position과 altitude값을 통해 viewpoint값(화면좌표)를 추출합니다.
	var viewpoint = projection.viewpointFromCoords(rvCustomOverlay.getPosition(), rvCustomOverlay.getAltitude());
	
	rv.setViewpoint(viewpoint); //커스텀 오버레이를 로드뷰의 가운데에 오도록 로드뷰의 시점을 변화 시킵니다.
	});
	
	</script>
	
	
	${ title } <br/>
	${ lat } <br/>
	${ lng } <br/>
	${ description } <br/>

</body>
</html>