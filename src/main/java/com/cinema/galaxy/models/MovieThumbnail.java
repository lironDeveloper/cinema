package com.cinema.galaxy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Table(name = "movie_thumbnail")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class MovieThumbnail {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Lob
        @Column(name = "thumbnail", columnDefinition="LONGBLOB")
        private byte[] thumbnail;
        @Column(name = "created_on")
        @CreationTimestamp
        private Instant createdOn;
        @Column(name = "updated_on")
        @UpdateTimestamp
        private Instant lastUpdatedOn;

                @OneToOne(mappedBy = "movieThumbnail", cascade = CascadeType.ALL, orphanRemoval = true)
        private Movie movie;
}
