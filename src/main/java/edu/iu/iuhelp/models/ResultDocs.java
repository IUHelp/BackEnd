package edu.iu.iuhelp.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


/**
 * Created by Sujeet on 12/5/16.
 */

@Component
@NoArgsConstructor
@AllArgsConstructor
public class ResultDocs {

        @Getter @Setter private float score;
        @Getter @Setter private String link;
        @Getter @Setter private String title;
        @Getter @Setter private String content;

}
