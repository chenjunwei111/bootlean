"use strict";
(function (window,doc) {
  window.contextMenu=window.contextMenu?window.contextMenu:{}
    var Menu;
   var maxlength=1;
    doc.addEventListener("click", function(e) {
        if(e.target.parentNode&&e.target.parentNode.id&&
            (e.target.parentNode.id=='r-down-menu-item'||
            e.target.parentNode.id=='r-up-menu-item'||
            (e.target.className=='menu-item-name'&& e.target.id==""))
        )
            return;

        $(".web-context-menu").remove();
    });
    contextMenu.addMenus=function (MenuArr,selector) {
        if (!MenuArr || MenuArr.length == 0)
            return;
       // var obj = doc.querySelector(selector);
        $(".web-context-menu").remove();
     // console.log( doc.querySelector(".web-context-menu")) ;
            createMenu(selector,MenuArr);
            Menu = doc.querySelector(".web-context-menu");
    }

    var createMenuItem = function(ms) {
        // 创建菜单项
        var menuItem = doc.createElement("div");
        menuItem.setAttribute("class", "web-context-menu-item");
        menuItem.setAttribute("id", ms.id);
        if(ms.serial)
            menuItem.setAttribute("serial", ms.serial);
        if(ms.viewer)
        menuItem.setAttribute("style", "display:"+ms.viewer);
        // 菜单项中的span，菜单名
        var span = doc.createElement("span");
        span.setAttribute("class", "menu-item-name");
        span.innerText = ms.name;
        if (ms.callback) {
            if(typeof ms.callback === 'function')
                span.addEventListener("click", ms.callback);
        }
        // 创建小箭头
        var i = doc.createElement("i");
        i.innerText = "▲";
        span.appendChild(i);
        // 创建下一层菜单的容器
        var subContainer = doc.createElement("div");
        subContainer.setAttribute("class", "web-context-menu-items");

        menuItem.appendChild(span);
        menuItem.appendChild(subContainer);
        return menuItem;
    };

    // 创建菜单项之间的分隔线条
    var createLine = function(pro) {
        var line = doc.createElement("div");
        line.setAttribute("class", "menu-item-line");
        if (pro.viewer)
            line.setAttribute("style", "display:" + pro.viewer);
        if(pro.serial)
            line.setAttribute("serial", pro.serial);
        return line;
    };

    /**
     * 创建菜单
     */
    var createMenu = function(Selectorid, menuArr) {
        // 创建菜单层
        var menu = doc.createElement("div");
        menu.setAttribute("class", "web-context-menu");
        if(!Selectorid||Selectorid==="body")
          doc.querySelector("body").appendChild(menu);
        else
            doc.querySelector("#"+Selectorid).appendChild(menu);
        // 创建菜单项容器
        var menuItemsContainer = doc.createElement("div");
        menuItemsContainer.setAttribute("class", "web-context-menu-items");
        menu.appendChild(menuItemsContainer);
        $.each(menuArr,function (i,m) {
            var parent = m.parent;
            // 创建菜单项
            var oneMenu = createMenuItem(m);
            if (!parent) {
                menuItemsContainer.appendChild(oneMenu);
                menuItemsContainer.appendChild(createLine(m));
            } else {
                var parentNode = doc.querySelector("#" + parent + " .web-context-menu-items");
                parentNode.appendChild(oneMenu);
                parentNode.appendChild(createLine(m));
            }
        })
        // 遍历菜单项去掉没有子菜单的菜单项的小箭头
        var allContainer = menu.querySelectorAll(".web-context-menu-items");
        //maxlength=allContainer.length;
        for (var i = 0; i < allContainer.length; i++) {
            var oneContainer = allContainer[i];
            if (!oneContainer.hasChildNodes()) {
                var iTag = oneContainer.parentElement.querySelector("i")
                iTag.parentElement.removeChild(iTag);
                oneContainer.parentNode.firstChild.id="last-menu-item";
            }
        }
    }

    //显示菜单
    contextMenu.showMenus = function(event) {
        if(event&&Menu)
        {
            Menu.style.display = "block";
            Menu.style.position="absolute";
            var x = event.offsetX;
            var y = event.offsetY;
            // var x = event.clientX;
            // var y = event.clientY;
            // 调整菜单出现位置，防止菜单溢出浏览器可视区域
            if (y + Menu.clientHeight*maxlength > window.innerHeight) {
                y -= Menu.clientHeight*maxlength;
            }
            if (x + Menu.clientWidth*maxlength > window.innerWidth) {
                x -= Menu.clientWidth*maxlength;
            }
            Menu.style.left = x + "px";
            Menu.style.top = y + "px";
        }
    };
})(window,document);