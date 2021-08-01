package by.tms.quizletclone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "CARDS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Term cannot be empty")
    @Column(name = "term")
    private String term;

    @NotBlank(message = "Definition cannot be empty")
    @Column(name = "definition")
    private String definition;

    private String filename;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private LearnModel model;

}
