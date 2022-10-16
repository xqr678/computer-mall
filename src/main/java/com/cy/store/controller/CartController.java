package com.cy.store.controller;

import com.cy.store.entity.OrderItem;
import com.cy.store.service.ICartService;
import com.cy.store.util.JsonResult;
import com.cy.store.vo.CartVO;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.awt.*;
import java.util.List;

/**
 * @author XQR
 * @date 2022/10/15 2022/10/15
 * @dsecription 类的描述和介绍
 */
@RestController
@RequestMapping("carts")
public class CartController extends BaseController {
    @Autowired
    private ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session){
        cartService.addToCart(getUidFromSession(session),pid,amount,getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }

    @RequestMapping({"","/"})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session){
        List<CartVO> data = cartService.getVOByUid(getUidFromSession(session));
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable Integer cid, HttpSession session){
        Integer data=cartService.addNum(cid,getUidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("{cid}/num/reduce")
    public JsonResult<Integer> reduceNum(@PathVariable Integer cid, HttpSession session){
        Integer data=cartService.reduceNum(cid,getUidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("list")
    public JsonResult<List<CartVO>> getVOByCid(Integer[] cids,HttpSession session){
        List<CartVO> data = cartService.getVOByCids(getUidFromSession(session),cids);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("{cids}/delete")
    public JsonResult<List<Integer>> deleteByUidAndCid(@PathVariable Integer[] cids){
        cartService.deleteByUidAndCid(cids);
        return new JsonResult<>(OK);
    }

    @RequestMapping("{oids}/listItem")
    public JsonResult<List<OrderItem>> getVOByOid(@PathVariable Integer[] oids){
        List<OrderItem> data = cartService.findByOids(oids);
        return new JsonResult<>(OK,data);
    }






 }
