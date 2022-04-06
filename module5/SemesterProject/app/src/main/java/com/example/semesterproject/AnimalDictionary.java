package com.example.semesterproject;

import java.util.HashMap;
import java.util.Map;

public class AnimalDictionary {
    private Map<String, Animal> _animalDictionary;

    public AnimalDictionary() {
        _animalDictionary = new HashMap<>();
        _animalDictionary.put("monkey", new Animal(0x1F435, null));
        _animalDictionary.put("dog", new Animal(0x1F436, null));
        _animalDictionary.put("cat", new Animal(0x1F431, null));
        _animalDictionary.put("lion", new Animal(0x1F981, null));
        _animalDictionary.put("tiger", new Animal(0x1F42F, null));
        _animalDictionary.put("horse", new Animal(0x1F434, null));
        _animalDictionary.put("unicorn", new Animal(0x1F984, null));
        _animalDictionary.put("cow", new Animal(0x1F42E, null));
        _animalDictionary.put("pig", new Animal(0x1F437, null));
        _animalDictionary.put("mouse", new Animal(0x1F42D, null));
        _animalDictionary.put("rabbit", new Animal(0x1F430, null));
        _animalDictionary.put("bear", new Animal(0x1F43B, null));
        _animalDictionary.put("panda", new Animal(0x1F43C, null));
        _animalDictionary.put("koala", new Animal(0x1F428, null));
    }

    public Map<String, Animal> getAnimalDictionary() {
        return _animalDictionary;
    }
}
