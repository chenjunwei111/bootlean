"use strict";

(function(window, doc) {
	window.maptool = window.maptool ? window.maptool : {};
	maptool.setSectorPosition = function(varl) {
		var arr = [ varl ];
		var p = stylemap.setSingleSectorStyle(arr, stylemap.SName.POSITION);
		return p;
	};

	// 监听键盘事件
	maptool.keyListener = function(Listencls) {
		$("." + Listencls).keyup(function(event) {
			console.log(event);
			if (event.ctrlKey && event.keyCode === 65) {// Ctrl+A
			} else if (event.ctrlKey && event.keyCode === 83) {// Ctrl+S

			} else if (event.ctrlKey && event.keyCode === 70) {// Ctrl+F

			} else if (event.ctrlKey && event.keyCode === 71) {// Ctrl+G
				position_open(0);
			} else if (event.keyCode === 13 || event.keyCode === 108) {// Enter

			} else if (event.keyCode === 27) {// Esc

			}
		});
	};

	// 查找
	var f = {};
	maptool.position_open = function(item, e) {
		$(".p-content-text").focus();
		$(".p-content-text").val("");
		f = {};
		if (item == 0) {
			$(".p-label").text("小区定位");
			$(".p-content-label").text("小区");
			$(".p-content-text").attr("placeholder", "小区ID/小区名");
		} else if (item == 1) {
			$(".p-label").text("栅格定位");
			$(".p-content-label").text("ID");
			$(".p-content-text").attr("placeholder", "栅格ID");
		} else if (item == 2) {
			$(".p-label").text("经纬度定位");
			$(".p-content-label").text("经纬");
			$(".p-content-text").attr("placeholder", "0.000000,0.000000");
		} else if (item == 3) {
			$(".p-label").text("地址查询(模糊)");
			$(".p-content-label").text("地址");
			$(".p-content-text").attr("placeholder", "(地址1,地址2)");
		} else if (item == 4) {
			$(".p-label").text("POI场景定位");
			$(".p-content-label").text("场景名");
			$(".p-content-text").attr("placeholder", "场景名");
		}
		f = {
			findidx : item,
			e : e
		};

		// changeToolStatue(e);
		$("#map-position-id").fadeIn(1000);
	}

	maptool.findMapInfo = function() {
		lyrmgr.delMapLayerbyType(lyrmgr.LType.SYMBOLMAP);
		var info = $(".p-content-text").val();
		if (info.trim() == "") {
			msg("请输入信息查询.");
			return;
		}
		// 小区查找
		if (f.findidx == 0) {
			setFindSite(info.trim())
			// var rsl = maptool.setSectorFlag([info.trim()]);
			// if (rsl.length == 0) {
			// msg("不存在该小区.");
			// return;
			// }
			// basemap.mapvw.panTo(new BMap.Point(rsl[0].coord[0], rsl[0].coord[1]));
			// rsl = null;
		}
		// //栅格查找
		else if (f.findidx == 1) {

		} else if (f.findidx == 2) {
			var lnglat = info.split(',');
			if (lnglat.length != 2) {
				msg("经纬度格式错误.");
				return;
			}
			var reglng = /^(((\d|[1-9]\d|1[0-7]\d|0)\.\d{0,10})|(\d|[1-9]\d|1[0-7]\d|0{1,3})|180\.0{0,10}|180)$/;
			var reglat = /^([0-8]?\d{1}\.\d{0,10}|90\.0{0,10}|[0-8]?\d{1}|90)$/;
			if (!reglat.test(lnglat[1]) || !reglng.test(lnglat[0])) {
				msg("经/纬度值错误,小数应该6位内.");
				return;
			}
			var rp = maptool.setMarkFlag({
				lng : lnglat[0],
				lat : lnglat[1]
			});
			if (!rp) {
				msg("无法找到经纬度.");
				return;
			}
			basemap.mapvw.panTo(new BMap.Point(rp.lng, rp.lat));
		} else if (f.findidx == 3) {

			// var myGeo = new BMap.Geocoder();
			// 将地址解析结果显示在地图上,并调整地图视野
			// myGeo.getPoint(info, function(point){
			// if (point) {
			// console.log(point);
			// var k = maptool.setMarkFlag(point);
			// basemap.mapvw.panTo(new BMap.Point(k.lng, k.lat));
			// }else{
			// tips("您选择地址没有解析到结果!");
			// }
			// }, basemap.VMapCfg.mapCity);

			var ls = new BMap.LocalSearch(basemap.VMapCfg.mapCity);
			ls.clearResults();
			ls.search(info);
			ls.enableAutoViewport();
			ls.setSearchCompleteCallback(function(rs) {
				if (ls.getStatus() == BMAP_STATUS_SUCCESS) {
					console.log(rs);
					addresspoi(rs);
					// var rsl = rs.getCurrentNumPois();
					// console.log(rsl);
					// for (var p = 0; p < rsl; p++) {
					// var poi = rs.getPoi(p);
					// //poi.address
					// //poi.point.lng/lat
					// // poi.title
					// console.log(poi);
					// }
				} else {
					msg("无法查询该地址.");
				}
			});
		}
		maptool.position_close(f.e);
		f = {};
	}

	/*
	 * 测距
	 */
	var mdis = null;
	maptool.measure_open = function(e) {
		if (mdis) {
			mdis.close();
			mdis = null;
			basemap.setBoxStatue(e, 0);
			// changeToolClose();
		} else {
			// /*{"followText" : {String} 测距过程中，提示框文字,
			// "unit" : {String} 测距结果所用的单位制，可接受的属性为"metric"表示米制和"us"表示美国传统单位,
			// "lineColor" : {String} 折线颜色,
			// "lineStroke" : {Number} 折线宽度,
			// "opacity" : {Number} 透明度,
			// "lineStyle" : {String} 折线的样式，只可设置solid和dashed,
			// "secIcon" : {BMap.Icon} 转折点的Icon,
			// "closeIcon" : {BMap.Icon} 关闭按钮的Icon,
			// "cursor" : {String} 跟随的鼠标样式}
			// */
			// mdis = new BMapLib.DistanceTool(basemap.mapvw,
			// {
			// unit: 'metric',
			// opacity: 0.8,
			// followText: '点击图标关闭',
			// closeIcon: ''
			// });
			// mdis.open();
			mdis = new MeasureTool.distance(basemap.mapvw);
			mdis.open();
		}
	}

	/*
	 * 图层管理
	 */
	maptool.ShowLayerMgr = function(e, map) {
		var layer = [ {
			name : "图层列表",
			code : "LYR-MGR",
			icon : "tree-icon-level-th",
			child : [ {
				name : "小区图层",
				icon : "tree-icon-level-minus-sign",
				code : "LYR-SECTOR",
				parentCode : "LYR-MGR",
				child : []
			}, {
				name : "数据图层",
				icon : "tree-icon-level-minus-sign",
				code : "LYR-DATA",
				parentCode : "LYR-MGR",
				child : []
			}, {
				name : "标注图层",
				icon : "tree-icon-level-minus-sign",
				code : "LYR-LABEL",
				parentCode : "LYR-MGR",
				child : []
			} ]
		} ];
		var has = [];

		var id = (map && map.getContainer().id) || undefined;
		var layers = lyrmgr.getMapLayer(id);
		if (layers) {
			$.each(layers, function(i, o) {
				if ($.inArray(o.mcode, has) == -1) {
					if (o.mtype === lyrmgr.LType.SITEMAP || o.mtype === lyrmgr.LType.SECTORMAP) {
						layer[0].child[0].child.push({
							name : o.name,
							icon : o.viewer ? "tree-icon-view-open-sign" : "tree-icon-view-close-sign",
							code : o.mcode,
							parentCode : 'LYR-SECTOR',
							child : []
						})
					} else if (o.mtype === lyrmgr.LType.LABELMAP) {
						layer[0].child[2].child.push({
							name : o.name,
							icon : o.viewer ? "tree-icon-view-open-sign" : "tree-icon-view-close-sign",
							code : o.mcode,
							parentCode : 'LYR-LABEL',
							child : []
						})
					} else {// if (o.mtype === lyrmgr.LType.NETWORK)
						layer[0].child[1].child.push({
							name : o.name,
							icon : o.viewer ? "tree-icon-view-open-sign" : "tree-icon-view-close-sign",
							code : o.mcode,
							parentCode : 'LYR-DATA',
							child : []
						})
					}

					if (o.mlabel != undefined) {
						layer[0].child[2].child.push({
							name : o.mlabel.name,
							icon : o.mlabel.viewer ? "tree-icon-view-open-sign" : "tree-icon-view-close-sign",
							code : o.mcode + '-$label',
							parentCode : 'LYR-LABEL',
							child : []
						})
					}
					has.push(o.mcode);
				}
			});
		}
		// //
		addTree(layer, 'lay-content-id', viewLayer, id);
		$((id ? "#" + id : '') + " .map-layermgr").css("display", "block");
		// console.log("layermgr");
		// console.log($(".map-layermgr"));
		// $(".map-layermgr").css("height", $(".map-layermgr").get(0).offsetHeight);
		// $(".map-layermgr").css("width", $(".map-layermgr").get(0).offsetWidth);
		// changeToolStatue(e);
	}

	function viewLayer(code, elem) {

		var map = (elem && !jQuery.isEmptyObject($m) && $m.mv[elem]) || undefined;
		// 标注层
		if (code.indexOf('-$label') >= 0) {
			viewLabel(code,map);
			return;
		}
		var a = lyrmgr.getMapLayerbyCode(code, elem);
		if (!a)
			return;
		// var code= $('.'+className).closest('li').attr('data-name');
		$.each(a, function(i, j) {
			var lyr = lyrmgr.getLayer(j.mcode, map);
			if (lyr) {
				if (j.viewer) {
					lyr.hide();
					j.viewer = false;
                    lyr.viewer = false;
					Legends.HideLegend(code,elem);
				} else {
					lyr.show();
					j.viewer = true;
                    lyr.viewer = true;
					Legends.ShowLegend(code,elem);
				}
			}
		})
	}

	function viewLabel(code,map) {
        code = code.replace('-$label', '');
		var a = lyrmgr.getMapLayerbyCode(code);
		if (!a)
			return;
		$.each(a, function(i, j) {
			if (j.mlabel) {
				if (j.mlabel.viewer) {
					var lyr = lyrmgr.getLayer(j.mlabel.mcode,map);
					if (lyr)
						lyr.remove();
					j.mlabel.viewer = false;
				} else {
					var l = new mapLabel.Label();
					if (j.mlabel.data) {
						l.setData(map, j.mlabel);
					} else {
						l.setLayer(lyrmgr.getLayer(j.mcode,map), j.mlabel);
					}
					j.mlabel.viewer = true;
				}
			}
		});
	}

	/*
	 * 拉框放大
	 */
	var reczoomout = null;
	var ztype = 0;
	maptool.rec_zoomout = function(e, zt) {
		if (reczoomout) {
			if (ztype == zt) {
				reczoomout.close();
				reczoomout = null;
				ztype = 0;
				basemap.setBoxStatue(e, 0);
			}
		} else {
			reczoomout = new BMapLib.RectangleZoom(basemap.mapvw, {
				zoomType : zt,
				strokeWeight : 1,
				strokeColor : 'red',
				followText : '点击图标关闭',
				opacity : 0.8,
				autoClose : false
			});
			reczoomout.open();
			ztype = zt;
			// changeToolStatue(e);
		}
	}

	/*
	 * 地址查询
	 */
	function addresspoi(rs) {
		$("#set-address-poi-id").empty();
		var datas = [];
		var rsl = rs.getCurrentNumPois();
		for (var p = 0; p < rsl; p++) {
			var poi = rs.getPoi(p);
			datas.push([ poi.title, poi.point.lng + ',' + poi.point.lat, poi.address ])
		}
		var tab = commdiv.$table({
			classname : "address-info",
			classtype : "table table-condensed table-hover table-spriped table-bordered ",//
			header : [ "标题", "经纬度", "地址" ],
			hrefheader : {
				headerid : 1,
				callback : function(e) {
					setMarkPOI(e);
				}
			},
			data : datas
		});

		if (tab) {
			tab.setAttribute("style", "width: 100%;height:100%;overflow-y:auto");
			$("#set-address-poi-id").append(tab);
			$("#map-address-poi-id").css('display', 'block');
			commdiv.$setDragDiv('map-address-poi-id');
		}
	}

	function setMarkPOI(e) {
		var target = e.target.innerText.split(',');
		if (target.length == 2) {
			// var coord = coordtransform.bd09towgs84(target[0], target[1]);
			var coord = {
				lng : target[0],
				lat : target[1],
				deg : 359
			};
			var k = maptool.setMarkFlag(coord);
			basemap.mapvw.panTo(new BMap.Point(k.lng, k.lat));
		}
	}

	maptool.position_close = function(e) {
		$("#map-position-id").fadeOut(200);
		basemap.setBoxStatue(e, 0);
		// changeToolClose();
	}
	maptool.property_close = function(e) {
		$(".map-property").fadeOut(200);
		setTimeout(function() {
			$("#set-property-id").empty();
			$("#sel-info-btn-id").attr("value", "");

		}, 1000);
		basemap.setBoxStatue(e, 0);
		// changeToolClose();
	}
	maptool.LayerMgr_close = function(e) {
		$(".map-layermgr").fadeOut(200);
		setTimeout(function() {
			$("#lay-content-id").empty();
			$("#map-layermgr-id").css('height', 'initial');
			$("#map-layermgr-id").css('width', 'initial');
		}, 1000);
		basemap.setBoxStatue(e, 0);
		// changeToolClose();
	}
	maptool.addresspoi_close = function(e) {
		$("#map-address-poi-id").fadeOut(200);
		setTimeout(function() {
			$("#set-address-poi-id").empty();
		}, 1000);
		basemap.setBoxStatue(e, 0);
		// changeToolClose();
	}
	/*
	 * 清除图层
	 */
	maptool.ClearStyle = function() {
		// lyrmanager.delMapLayerbyType(lyrmanager.LyrType.STYLEMAP);
		// lyrmanager.delMapLayerbyType(lyrmanager.LyrType.DRAWMAP);
		// Legend.CloseLegend();
		$("#sel-info-btn-id").attr("value", "");
		$('#blayer i').removeAttr('class');
		$('#blayer dd').removeClass('mtool-disabled');

	}

	maptool.zoomout = function() {
		if (basemap.mapvw)
			basemap.mapvw.zoomIn();
	}
	maptool.zoomin = function() {
		if (basemap.mapvw)
			basemap.mapvw.zoomOut();
	}

	function clearSeletedSymbol() {
		lyrmgr.delMapLayerbyType(lyrmgr.LType.SYMBOLMAP);
		$Layer.removeLayer("pan-flag")
		$("#sel-info-btn-id").attr("value", '');
	}

	function setFindSite(text) {
		clearSeletedSymbol();
		$.ajax({
			type : 'post',
			url : '/contra/PLteSector/QuerySite',
			data : {
				cityCode : basemap.VMapCfg.mapCityCode,
				City : basemap.VMapCfg.mapCity,
				Date : basemap.VMapCfg.mapDate,
				Query : text
			},
			success : function(rs) {
				if (!rs || rs.length == 0) {
					return msg("不存在该小区.");
				}
				var cd = coordtransform.wgs84tobd09(rs[0].LNG, rs[0].LAT);
				stylemap.setFlagOverlayStyle([ {
					lng : cd.lng,
					lat : cd.lat,
					deg : rs[0].DIR
				} ], true);
				$("#sel-info-btn-id").attr("value", text + '(' + rs[0].DIR + '°)');
				basemap.mapvw.panTo(new BMap.Point(cd.lng, cd.lat));
			}
		})
	}
	/*
	 * 设置小区定位样式 返回点坐标
	 */
	maptool.setSectorFlag = function(objects) {
		clearSeletedSymbol();
		if (!objects || objects.length == 0)
			return;
		var wc = [];

		var info = sectormap.getSectorInfo(objects);
		$.each(info, function(a, b) {
			wc.push({
				lng : b.coord[0],
				lat : b.coord[1],
				deg : b.deg
			})
		})
		if (wc.length > 0) {
			stylemap.setFlagOverlayStyle(wc, true);
			$("#sel-info-btn-id").attr("value", info[0].sector + '(' + info[0].deg + '°)');
		}
		return info;
	}

	/*
	 * point: lng lat deg
	 */
	maptool.setMarkFlag = function(point) {
		clearSeletedSymbol();
		var wc = [];
		// var coord = coordtransform.wgs84tobd09(point.lng, point.lat);
		// coord.deg=359;
		point.deg = point.deg || 359;
		wc.push(point)
		stylemap.setFlagOverlayStyle(wc);
		return point;
	}
})(window, document);
