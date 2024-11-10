@Data
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private double price;

    @Column(name="additional_price")
    private double additionalPrice;
}