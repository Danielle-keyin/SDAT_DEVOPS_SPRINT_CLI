@Entity
public class City {
    @Id @GeneratedValue private Long id;
    @Column(nullable=false) private String name;
    private String state;
    private Integer population;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Airport> airports = new ArrayList<>();
}
