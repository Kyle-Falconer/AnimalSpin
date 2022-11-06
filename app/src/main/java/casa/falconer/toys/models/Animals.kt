package casa.falconer.toys.models

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import casa.falconer.toys.R

enum class Animal(@DrawableRes val image: Int, @StringRes val tts_says: Int) {
    CAT(R.drawable.cat_1, R.string.tts_cat_says),
    CHICKEN(R.drawable.hen_chicken, R.string.tts_chicken_says),
    COW(R.drawable.cow_female_black_white, R.string.tts_cow_says),
    DOG(R.drawable.dog_1, R.string.tts_dog_says),
    DONKEY(R.drawable.donkey_in_clovelly_north_devon_england, R.string.tts_donkey_says),
    FROG(R.drawable.the_green_and_golden_bell_frog, R.string.tts_frog_says),
    HORSE(R.drawable.domestic_horse_essenpas_bemmel, R.string.tts_horse_says),
    LION(R.drawable.young_african_lion_33560925282, R.string.tts_lion_says),
    MONKEY(R.drawable.monkey_portrait_animal, R.string.tts_monkey_says),
    SHEEP(R.drawable.pexels_ellie_burgin_10895600, R.string.tts_sheep_says),
}

class AnimalNoise(val animal: Animal, @RawRes val noiseFile: Int) {

    companion object {
        val animal_sounds = listOf(
            AnimalNoise(Animal.CAT, R.raw.cat_15),
            AnimalNoise(Animal.CAT, R.raw.cat_20),
            AnimalNoise(Animal.CAT, R.raw.cat_52),
            AnimalNoise(Animal.CAT, R.raw.cat_68),
            AnimalNoise(Animal.CAT, R.raw.cat_76),
            AnimalNoise(Animal.CAT, R.raw.cat_93),
            AnimalNoise(Animal.CHICKEN, R.raw.chicken_2),
            AnimalNoise(Animal.CHICKEN, R.raw.chicken_3),
            AnimalNoise(Animal.CHICKEN, R.raw.chicken_9),
            AnimalNoise(Animal.CHICKEN, R.raw.chicken_10),
            AnimalNoise(Animal.CHICKEN, R.raw.chicken_11),
            AnimalNoise(Animal.CHICKEN, R.raw.chicken_12),
            AnimalNoise(Animal.COW, R.raw.cow_1),
            AnimalNoise(Animal.COW, R.raw.cow_2),
            AnimalNoise(Animal.COW, R.raw.cow_3),
            AnimalNoise(Animal.COW, R.raw.cow_4),
            AnimalNoise(Animal.DOG, R.raw.dog_9),
            AnimalNoise(Animal.DOG, R.raw.dog_40),
            AnimalNoise(Animal.DOG, R.raw.dog_74),
            AnimalNoise(Animal.DOG, R.raw.dog_86),
            AnimalNoise(Animal.DOG, R.raw.dog_87),
            AnimalNoise(Animal.DOG, R.raw.dog_89),
            AnimalNoise(Animal.DONKEY, R.raw.donkey_1),
            AnimalNoise(Animal.DONKEY, R.raw.donkey_2),
            AnimalNoise(Animal.DONKEY, R.raw.donkey_3),
            AnimalNoise(Animal.DONKEY, R.raw.donkey_4),
            AnimalNoise(Animal.FROG, R.raw.frog_1),
            AnimalNoise(Animal.FROG, R.raw.frog_3),
            AnimalNoise(Animal.FROG, R.raw.frog_4),
            AnimalNoise(Animal.FROG, R.raw.frog_5),
            AnimalNoise(Animal.FROG, R.raw.frog_6),
            AnimalNoise(Animal.HORSE, R.raw.single_horse_whinny_ee100701),
            AnimalNoise(Animal.HORSE, R.raw.horse_neigh1),
            AnimalNoise(Animal.HORSE, R.raw.horse_neigh3),
            AnimalNoise(Animal.HORSE, R.raw.horse_neigh5),
            AnimalNoise(Animal.HORSE, R.raw.horse_neigh6),
            AnimalNoise(Animal.LION, R.raw.lion_2),
            AnimalNoise(Animal.LION, R.raw.lion_5),
            AnimalNoise(Animal.LION, R.raw.lion_6),
            AnimalNoise(Animal.LION, R.raw.lion_7),
            AnimalNoise(Animal.LION, R.raw.lion_34),
            AnimalNoise(Animal.LION, R.raw.lion_44),
            AnimalNoise(Animal.MONKEY, R.raw.monkey_10),
            AnimalNoise(Animal.MONKEY, R.raw.monkey_12),
            AnimalNoise(Animal.MONKEY, R.raw.monkey_14),
            AnimalNoise(Animal.MONKEY, R.raw.monkey_15),
            AnimalNoise(Animal.MONKEY, R.raw.monkey_17),
            AnimalNoise(Animal.MONKEY, R.raw.monkey_18),
            AnimalNoise(Animal.SHEEP, R.raw.sheep_1),
            AnimalNoise(Animal.SHEEP, R.raw.sheep_4),
            AnimalNoise(Animal.SHEEP, R.raw.sheep_5),
            AnimalNoise(Animal.SHEEP, R.raw.sheep_18),
            AnimalNoise(Animal.SHEEP, R.raw.sheep_26),
            AnimalNoise(Animal.SHEEP, R.raw.sheep_27),
        )
    }
}
