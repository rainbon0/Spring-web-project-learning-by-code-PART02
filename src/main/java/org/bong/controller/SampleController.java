package org.bong.controller;


import lombok.extern.log4j.Log4j;
import org.bong.domain.SampleDTO;
import org.bong.domain.SampleDTOList;
import org.bong.domain.TodoDTO;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;


/* servlet-context의 component-scan이 org.bong.controller패캐지를 검사.
*  스프링에서 객체 설정에 사용되는 어노테이션(@Controller...)들을 가진 클래스들을 파악하고 필요할시 이를 객체로 생성해서 관리한다.*/
@Controller
@Log4j
@RequestMapping("/sample/*") // /sapmple/etc 와 같은 URL은 모두 이 클래스에서 처리
public class SampleController {
    @RequestMapping(value="/basic", method = {RequestMethod.GET, RequestMethod.POST})
    public void basic(){
        log.info("basic............");
    }

    @GetMapping("/basicOnlyGet")
    public void basicGet2(){
        log.info("basic get only get......");
    }
    // @GetMapping("etc") = @RequestMapping(value="etc", method={RequestMethod.GET})

    @GetMapping("/ex01")
    public String ex01(SampleDTO dto){

        log.info(""+dto);
        return "ex01";
    }

    @GetMapping("/ex02")
    public String ex02(@RequestParam("name")String name , @RequestParam("age")int age){
        log.info("name : "+name);
        log.info("age : "+age);
        return "ex02";
        // 기본 자료형 혹은 문자열 등을 이용한다면 @RequestParam을 통해 위와 같이 파라미터를 받을 수 있다.

    }

    @GetMapping("/ex02List")
    public String ex02List  (@RequestParam("ids")ArrayList<String> idList){
        log.info("ids : " +idList);
        // INFO : org.bong.controller.SampleController - ids : [11, 22, 33]

        return "ex02List";

        // 동일한 타입의 파라미터가 여러 개 전달되는 경우 ArrayList 등을 이용해서 처리가 가능하다
        // @RequestParam은 보통 파라미터로 사용된 변수의 이름과 전달되는 파라미터의 이름이 다른 경우에 유용하게 사용된다.
    }

    @GetMapping("/ex02Array")
    public String ex02Array(@RequestParam("ids") String[] ids){
        log.info("array ids : " +ids);
        return "ex02Array";
    }

    @GetMapping("/ex02Map")
    public String ex02Map(@RequestParam HashMap<String,Integer> map){
        System.out.println("1 : " + map.get("ids1"));
        System.out.println("2 : " + map.get("ids2"));
        System.out.println("3 : " + map.get("ids3"));
        return "ex02Map";
        // ?ids1=11&ids2=22&ids3=33 같이 Key Value 형태로 파라미터를 구성하면 HashMap 등의 형태로도 파라미터를 처리할 수 있다.
    }

    @GetMapping("/ex02Bean")
    public String ex02Bean(SampleDTOList list){
        log.info("list dtos : " + list);
        return "ex02Bean";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));

    }

    @GetMapping("/ex03")
    public String ex03(TodoDTO todo){
        log.info("todo : " + todo);
        return "ex03";
    }

    @GetMapping("/ex04")
    public String ex04(SampleDTO dto, @ModelAttribute("page") int page){
        log.info("dto : " + dto);
        log.info("page : " + page);

        return "/sample/ex04";
    }

    @GetMapping("/eX05")
    public void ex05(){
        log.info("/ex05.......");
    }

    @GetMapping("/ex06")
    @ResponseBody
    public SampleDTO ex06(){
        log.info("/ex06........");
        SampleDTO dto = new SampleDTO();
        dto.setAge(10);
        dto.setName("Mr.Bong");
        return dto;
    }

    @GetMapping("/ex07")
    public ResponseEntity<String> ex07(){
        log.info("/ex07.........");

        String msg = "{\"name\": \"Mr.Bong\"}";

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<>(msg, header, HttpStatus.OK);
    }

    @GetMapping("/exUpload")
    public void exUpload(){
        log.info("/exUpload.......");
    }

    @PostMapping("/exUploadPost")
    public void exUploadPost(ArrayList<MultipartFile> files){
        files.forEach(file -> {
            log.info("-----------------");
            log.info("name : " + file.getOriginalFilename());
            log.info("size : " + file.getSize());

        });
    }
}
