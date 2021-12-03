package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

class Theme{
	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
class Comment{
	private Integer id;
	private  Integer id1;
	private String text;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId1() {
		return id1;
	}
	public void setId1(Integer id1) {
		this.id1 = id1;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
@SpringBootApplication
@RestController
public class DemoApplication {
	private List<Theme> themes = new ArrayList<>();
	private List<Comment> comments = new ArrayList<>();
	//http://localhost:8080/
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
//	$ curl -X POST http://localhost:8080/themes -d '{"id":1 , "name" : "first"}' -H "Content-Type:application/json"
	@PostMapping("/themes")//создание темы(и сразу выводит)
	public Theme createTheme(@RequestBody() Theme body){
		themes.add(body);//добавление тела(body) в themes
		return body;
	}
//	$ curl -X POST http://localhost:8080/themes -d '{"id":1 , "name" : "first"}' -H "Content-Type:application/json"
	@GetMapping("/themes")// вывод списка
	public List<Theme> listThemes(){
		return themes;
	}

//	$ curl -X POST http://localhost:8080/themes  -d '{"id":1 , "name" : "first"}' -H "Content-Type:application/json"
//  $ curl -X DELETE http://localhost:8080/themes
	@DeleteMapping("/themes")// удаление темы
	public void deleteThemesAll(){
		themes.removeAll(themes);
	}
//	$ curl -X POST http://localhost:8080/themes  -d '{"id":0 , "name" : "first"}' -H "Content-Type:application/json"
//	$ curl -X POST http://localhost:8080/themes  -d '{"id":1 , "name" : "second"}' -H "Content-Type:application/json"
//	$ curl -X POST http://localhost:8080/themes/1
	@DeleteMapping("/themes/{id}")// удаление темы
	public void deleteTheme(@PathVariable("id") Integer id){
		themes.remove((int)id);
	}

//	$ curl -X PUT http://localhost:8080/themes/0  -d '{"id":1 , "name" : "second"}' -H "Content-Type:application/json"
	@PutMapping("/themes/{id}")
	public Theme updateTheme(@PathVariable("id") Integer id, @RequestBody() Theme body){
		for (int i=0; i<themes.size();i++){
			if(themes.get(i).getId() ==id){
				themes.get(i).setName(body.getName());
				return themes.get(i);
			}
		}
		throw new RuntimeException("no theme");
	}

//	$ curl -X GET http://localhost:8080/themes/size
	@GetMapping("/themes/size")// вывод списка
	public int listThemesSize(){
		return themes.size();
	}

	// $ curl -X POST http://localhost:8080/themes  -d '{"id":3 , "name" : "new"}' -H "Content-Type:application/json"
	// $ curl -X GET http://localhost:8080/themes/1
	@GetMapping("/themes/{id}")
	public Theme createThemeID(@PathVariable("id") Integer id){
		return themes.get(id);
	}

	//$ curl -X POST http://localhost:8080/themes/comments  -d '{"id":3 ,"id1": 239, "text" : "comment1"}' -H "Content-Type:application/json"
	@PostMapping("/themes/comments")//создание коментария(и сразу выводит)
	public Comment createComment(@RequestBody() Comment body){
		comments.add(body);//добавление тела(body) в themes
		return body;
	}
	//$ curl -X GET http://localhost:8080/themes/comments
	@GetMapping("/themes/comments")// вывод списка
	public List<Comment> listComments(){
		return comments;}

	//$ curl -X DELETE http://localhost:8080/themes/comments
	@DeleteMapping("/themes/comments")// удаление темы
	public void deleteCommentsAll(){
		themes.removeAll(comments);
	}
// curl -X DELETE http://localhost:8080/themes/comments/0
	@DeleteMapping("/themes/comments/{id}")// удаление темы
	public void deleteComment(@PathVariable("id") Integer id){
		comments.remove((int)id);
	}

	//$ curl -X POST http://localhost:8080/themes/comments  -d '{"id":3 ,"id1": 239, "text" : "comment1"}' -H "Content-Type:application/json"
	//$ curl -X POST http://localhost:8080/themes/comments  -d '{"id":5 ,"id1": 239, "text" : "comment2"}' -H "Content-Type:application/json"
	//$ curl -X PUT http://localhost:8080/themes/comments/3  -d '{"id":10 ,"id1": 239, "text" : "comment13"}' -H "Content-Type:application/json"
	@PutMapping("/themes/comments/{id}")
	public Comment updateComment(@PathVariable("id") Integer id, @RequestBody() Comment body){
		for (int i=0; i<comments.size();i++){
			if(comments.get(i).getId() ==id){
				comments.get(i).setText(body.getText());
				return comments.get(i);
			}
		}
		throw new RuntimeException("no theme");
	}
//$ curl -X GET http://localhost:8080/themes/comments/size
	@GetMapping("/themes/comments/size")// вывод списка
	public int listCommentSize(){
		return comments.size();
	}
//$ curl -X GET http://localhost:8080/themes/comments/0
	@GetMapping("/themes/comments/{id}")
	public Comment createCommentID(@PathVariable("id") Integer id){
		return comments.get(id);
	}
}
