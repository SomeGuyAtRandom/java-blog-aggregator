package la.random.spring.web.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "blog")
public class Blog {

	private static final String className = "Blog";

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length = 1000)
	private String url;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "user_id") // name is used in this table
	private User user;
	
	@OneToMany(mappedBy="blog")
	private List<Item> items;
	

	public Blog() {
		System.out.println(className + "()");
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public List<Item> getItems() {
		return items;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}

}
