package App.Server;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
  @Autowired
  private UserRepo userRepository;
  @GetMapping("/")
  public List<User> GetUsers(){
    return userRepository.findAll();
  }
  @GetMapping("/{id}")
  public User GetUsers(@PathVariable String id){
    return userRepository.findById(id).orElse(null);
  }
  @PostMapping("/register")
  public User PostMapping(@RequestBody User user){
    return userRepository.save(user);
  }
  @PostMapping("/login")
  public User PostLogin(@RequestBody User user){
    List<User> users=userRepository.findAll();
    int n=users.size();
    for(int i=0;i<n;i++){
      if(user.getUsername().equals(users.get(i).getUsername())
        && user.getPassword().equals(users.get(i).getPassword()))
        return users.get(i);
    }
    user.setUsername("null");
    return user;
  }
  @PutMapping("/")
  public User PutMapping(@RequestBody User newUser){
    User oldUser=userRepository.findById(newUser.getId()).orElse(null);
    oldUser.setUsername(newUser.getUsername());
    oldUser.setEmail(newUser.getEmail());
    oldUser.setPassword(newUser.getPassword());
    return oldUser;
  }
  @DeleteMapping("/{id}")
  public String DeleteUser(@PathVariable String id){
    userRepository.deleteById(id);
    // userRepository.deleteAll();
    return id;
  }
}
