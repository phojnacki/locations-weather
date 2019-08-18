package com.demo;

import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocationControllerTest {
//
//    @Autowired
//    private LocationService locationService;
//
//    @Autowired
//    private WebTestClient webClient;
//
//    @Before
//    public void setup() throws Exception{
//         webClient = WebTestClient
//                .bindToController(new LocationController())
//                .configureClient().baseUrl("/person")
//                .build();
//    }
//
//    @Test
//    public void testCreate() {
//        Location location = new Location();
//        location.setName("abcd");
//        this.webClient.post().uri("/person/add")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .body(Mono.just(location),Location.class).exchange()
//                .expectStatus().isOk();
////                .expectBody(Location.class).isEqualTo(location);
////                .consumeWith(result -> assertEquals("Response result","abcd", result.getResponseBody().getName()));
//    }
//
    @Test
    public void testFindById() {
//        Location location = new Location();
//        location.setName("findById");
//        Location newLocation = locationService.add(Mono.just(location)).block();
//        this.webClient.get().uri("/person/{id}",newLocation.getId())
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody(Location.class).isEqualTo(newLocation);
    }
//
//    @Test
//    public void testList() {
//
//    }
//
//    @Test
//    public void testUpdate(){
//
//    }

} 