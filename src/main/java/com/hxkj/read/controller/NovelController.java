package com.hxkj.read.controller;

import com.hxkj.common.util.BaseController;
import com.hxkj.common.util.ToolString;
import com.hxkj.read.service.NovelService;
import com.jfinal.kit.StrKit;

import java.io.IOException;
import java.util.Map;


public class NovelController extends BaseController {

    public void index() {
        render("read/novel.html");
    }

    public void query() {
        Integer pageNumber = getParaToInt("page") == null ? 1 : getParaToInt("page");
        Integer limit = getParaToInt("rows") == null ? 20 : getParaToInt("rows");
        Integer start = (pageNumber - 1) * limit;

        String keyword = getPara("keyword");
        if (StrKit.isBlank(keyword)) {
            String category = StrKit.isBlank(getPara("category")) ? "gender=male&major=都市" : getPara("category");
            renderJson(NovelService.category(category, start, limit));
        } else {
            renderJson(NovelService.fuzzySearch(keyword, start, limit));
        }
    }

    public void novel() {
        String nid = getPara(0);
        renderJson(NovelService.novel(nid));
    }

    public void chapters() {
        String nid = getPara(0);
        renderJson(NovelService.chapters(nid));
    }

    public void chapter() {
        String url = getPara(0);
        renderJson(NovelService.chapter(url));
    }


    public void download() throws IOException {
        String nid = getPara("nid");
        String title = getPara("title");
        if (StrKit.isBlank(nid)) {
            renderText("小说id必须");
            return;
        }
        if (StrKit.isBlank(title)) {
            renderText("小说名字必须");
            return;
        }

        Map<String, Object> map = NovelService.saveInMapQuick(nid);
        Integer code = (Integer) map.get("code");
        if (code == -1) {
            renderText((String) map.get("message"));
            return;
        }
        StringBuilder sb = (StringBuilder) map.get("content");
        getResponse().setHeader("Accept-Ranges", "bytes");
        getResponse().setHeader("Content-Disposition", "attachment;" + ToolString.encodeFileName(getRequest(), title + ".txt"));
        renderText(sb.toString());

    }
}
