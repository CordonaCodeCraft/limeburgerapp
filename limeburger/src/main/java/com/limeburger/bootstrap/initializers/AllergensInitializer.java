package com.limeburger.bootstrap.initializers;

import com.limeburger.domain.allergen.entity.Allergen;
import com.limeburger.domain.allergen.service.AllergenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

import static com.limeburger.domain.allergen.entity.Allergen.AllergenType.*;

@Slf4j
@Component
public class AllergensInitializer extends Initializer {

  private static final String ALLERGENS_ICONS_PATH = IMAGES_PATH + "allergens/";

  public static List<Allergen> initializeAllergens(final AllergenService allergenService) {

    log.info(">>>>>>>>>> INITIALIZING ALLERGENS... <<<<<<<<< ");

    final Allergen crustacean =
        Allergen.builder()
            .allergenType(CRUSTACEAN)
            .description("Dangerous allergen, causing addiction to block chain")
            .imageUrl(ALLERGENS_ICONS_PATH + "corn.svg")
            .dangerIndex(10)
            .ingredients(new HashSet<>())
            .build();

    final Allergen egg =
        Allergen.builder()
            .allergenType(EGG)
            .description("Cute allergen causing addiction to cryptocurrency")
            .imageUrl(ALLERGENS_ICONS_PATH + "eggs.svg")
            .dangerIndex(2)
            .ingredients(new HashSet<>())
            .build();

    final Allergen fish =
        Allergen.builder()
            .allergenType(FISH)
            .description("Lethal allergen causing permanent affection to Hedera Hashgraph")
            .imageUrl(ALLERGENS_ICONS_PATH + "fish.svg")
            .dangerIndex(10)
            .ingredients(new HashSet<>())
            .build();

    final Allergen peanut =
        Allergen.builder()
            .allergenType(PEANUT)
            .description("Nasty allergen causing unexpected block chain behaviour")
            .imageUrl(ALLERGENS_ICONS_PATH + "peanut.svg")
            .dangerIndex(9)
            .ingredients(new HashSet<>())
            .build();

    final Allergen soybean =
        Allergen.builder()
            .allergenType(SOYBEAN)
            .description("Bad allergen causing unfriendly behaviour in office")
            .imageUrl(ALLERGENS_ICONS_PATH + "seeds.svg")
            .dangerIndex(8)
            .ingredients(new HashSet<>())
            .build();

    final Allergen nut =
        Allergen.builder()
            .allergenType(NUT)
            .description("Potentially lethal allergen provoking aggressive code reviews")
            .imageUrl(ALLERGENS_ICONS_PATH + "treenuts.svg")
            .dangerIndex(8)
            .ingredients(new HashSet<>())
            .build();

    final Allergen celery =
        Allergen.builder()
            .allergenType(CELERY)
            .description("Disturbing allergen causing technical interview failure")
            .imageUrl(ALLERGENS_ICONS_PATH + "treenuts.svg")
            .dangerIndex(9)
            .ingredients(new HashSet<>())
            .build();

    final Allergen mustard =
        Allergen.builder()
            .allergenType(CELERY)
            .description(
                "Fatal allergen triggering deadly butterfly effects on the cryptocurrency market")
            .imageUrl(ALLERGENS_ICONS_PATH + "dairy.svg")
            .dangerIndex(7)
            .ingredients(new HashSet<>())
            .build();

    final Allergen sesame =
        Allergen.builder()
            .allergenType(SESAME)
            .description("Potentially dangerous allergen causing zero tolerance to coffee")
            .imageUrl(ALLERGENS_ICONS_PATH + "seeds.svg")
            .dangerIndex(8)
            .ingredients(new HashSet<>())
            .build();

    final Allergen sulphur =
        Allergen.builder()
            .allergenType(SULPHUR)
            .description("Irritating allergen generating 404 errors")
            .imageUrl(ALLERGENS_ICONS_PATH + "shellfish.svg")
            .dangerIndex(6)
            .ingredients(new HashSet<>())
            .build();

    final Allergen lupin =
        Allergen.builder()
            .allergenType(LUPIN)
            .description("Bad allergen causing drama queen stereotype")
            .imageUrl(ALLERGENS_ICONS_PATH + "legumes.svg")
            .dangerIndex(3)
            .ingredients(new HashSet<>())
            .build();

    final Allergen mollusc =
        Allergen.builder()
            .allergenType(MOLLUSC)
            .description("Allergen causing death of imagination for fun allergen descriptions")
            .imageUrl(ALLERGENS_ICONS_PATH + "corn.svg")
            .dangerIndex(3)
            .ingredients(new HashSet<>())
            .build();

    final Allergen milk =
        Allergen.builder()
            .allergenType(MILK)
            .description("This allergen makes you hate coding")
            .imageUrl(ALLERGENS_ICONS_PATH + "corn.svg")
            .dangerIndex(10)
            .ingredients(new HashSet<>())
            .build();

    return allergenService.saveAll(
        List.of(
            crustacean,
            egg,
            fish,
            peanut,
            soybean,
            nut,
            celery,
            mustard,
            sesame,
            sulphur,
            lupin,
            mollusc,
            milk));
  }
}
