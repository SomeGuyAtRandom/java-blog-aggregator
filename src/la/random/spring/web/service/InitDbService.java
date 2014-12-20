package la.random.spring.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import la.random.spring.web.entity.Role;
import la.random.spring.web.entity.User;
import la.random.spring.web.entity.Item;
import la.random.spring.web.entity.Blog;
import la.random.spring.web.repository.RoleRepository;
import la.random.spring.web.repository.UserRepository;
import la.random.spring.web.repository.ItemRepository;
import la.random.spring.web.repository.BlogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class InitDbService {

	private static final String className = "InitDbService";

	public InitDbService() {
		System.out.println(className + "()");
	}

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private BlogRepository blogRepository;

	@PostConstruct
	public void initDb() {
		System.out.println(className + ".initDb()");
		createAllRoles();
		createAdminUser();
		for(int i=1; i <=3; i++){
			createUser("user" + i);
		}
		// addXusers(9);
	}

	private void createAdminUser() {
		System.out.println(className + ".createAdminUser()");
		User admin = new User();
		admin.setName("admin");
		admin.setPassword("admin");
		admin.setEnabled(true);
		admin.setEmail("admin@admin.com");

		List<Role> roles = new ArrayList<Role>();

		roles.add(roleRepository.findByName("ROLE_USER"));
		roles.add(roleRepository.findByName("ROLE_ADMIN"));
		roles.add(roleRepository.findByName("ROLE_GUEST"));

		admin.setRoles(roles);
		userRepository.save(admin);

		List<Blog> blogs = new ArrayList<Blog>();
		int numberOfBlogs = getRandom(1, 7);

		for (int i = 1; i <= numberOfBlogs; i++) {
			Blog blog = getRandomBlog(admin, i);
			blogs.add(blog);
		}
		admin.setBlogs(blogs);
	}

	private void createUser(String name) {
		System.out.println(className + ".createUser()");
		User user = new User();
		user.setName(name);
		user.setPassword(name);
		user.setEnabled(true);
		user.setEmail(name + "@" + name+ ".com");

		List<Role> roles = new ArrayList<Role>();

		roles.add(roleRepository.findByName("ROLE_USER"));
		if(isHeads()){roles.add(roleRepository.findByName("ROLE_ADMIN"));}
		if(isHeads()){roles.add(roleRepository.findByName("ROLE_GUEST"));}

		user.setRoles(roles);
		userRepository.save(user);

		List<Blog> blogs = new ArrayList<Blog>();
		int numberOfBlogs = getRandom(1, 7);

		for (int i = 1; i <= numberOfBlogs; i++) {
			Blog blog = getRandomBlog(user, i);
			blogs.add(blog);
		}
		user.setBlogs(blogs);
	}

	private Item getRandomItem(Blog blog, int j) {
		Item item = new Item();
		item.setTitle(blog.getUser().getName() + " title " + j);
		item.setBlog(blog);
		item.setPublishedDate(new Date());
		item.setLink("http://itme" + j + "." + blog.getUrl());
		item.setDescription(getRandomDecription());
		itemRepository.save(item);
		return item;
	}

	private Blog getRandomBlog(User user, int i) {
		Blog blog = new Blog();
		blog.setName(user.getName() + " no " + i);
		blog.setUrl(getRandomUrl(user.getName()));
		blog.setUser(user);
		blogRepository.save(blog);

		int numberOfItems = getRandom(1, 7);
		List<Item> items = new ArrayList<Item>();
		for (int j = 1; j <= numberOfItems; j++) {
			Item item = getRandomItem(blog, j);
			items.add(item);
		}
		blog.setItems(items);
		blogRepository.save(blog);
		return blog;
	}

	private String getRandomUrl(String user) {
		String ret = user + getWord() + ".com";
		return ret;
	}

	private String getRandomDecription() {
		String ret = getSentance();
		return ret;
	}

	private void createAllRoles() {
		Role user = new Role();
		user.setName("ROLE_USER");
		roleRepository.save(user);

		Role admin = new Role();
		admin.setName("ROLE_ADMIN");
		roleRepository.save(admin);

		Role guest = new Role();
		guest.setName("ROLE_GUEST");
		roleRepository.save(guest);

	}

	private int getRandom(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	private boolean isHeads(int i) {
		int val = getRandom(0, 100);
		return val < i;
	}

	private boolean isHeads() {
		int val = getRandom(0, 1);
		return 0 == val;
	}

	private String getLowerRandomChar() {
		int i = getRandom(97, 122);
		return Character.toString((char) i);
	}

	private String getUpperRandomChar() {
		int i = getRandom(65, 90);
		return Character.toString((char) i);
	}

	private String getPunc() {

		if (isHeads(80)) {
			return ".";
		}
		return "?";
	}

	private String getWord() {
		int wordSize = getRandom(1, 15);
		String ret = "";
		if (isHeads(10)) {
			ret += getUpperRandomChar();
		}
		for (int i = 0; i <= wordSize; i++) {

			ret += getLowerRandomChar();
		}

		return ret;

	}

	private String getSentance() {
		int sentanceSize = getRandom(4, 10);
		String ret = getUpperRandomChar();

		for (int i = 0; i <= sentanceSize; i++) {
			ret += getWord() + " ";
		}
		ret += getWord();
		ret += getPunc();
		ret += " ";
		return ret;
	}

}
