package com.adventofcode2023.dec05;

import static java.util.stream.Collectors.toList;

import java.util.List;

class GardenMap {

    private final CategoryMap seedToSoilMap;
    private final CategoryMap soilToFertilizerMap;
    private final CategoryMap fertilizerToWaterMap;
    private final CategoryMap waterToLightMap;
    private final CategoryMap lightToTemperatureMap;
    private final CategoryMap temperatureToHumidityMap;
    private final CategoryMap humidityToLocationMap;

    GardenMap(
        CategoryMap seedToSoilMap,
        CategoryMap soilToFertilizerMap,
        CategoryMap fertilizerToWaterMap,
        CategoryMap waterToLightMap,
        CategoryMap lightToTemperatureMap,
        CategoryMap temperatureToHumidityMap,
        CategoryMap humidityToLocationMap
    ) {
        this.seedToSoilMap = seedToSoilMap;
        this.soilToFertilizerMap = soilToFertilizerMap;
        this.fertilizerToWaterMap = fertilizerToWaterMap;
        this.waterToLightMap = waterToLightMap;
        this.lightToTemperatureMap = lightToTemperatureMap;
        this.temperatureToHumidityMap = temperatureToHumidityMap;
        this.humidityToLocationMap = humidityToLocationMap;
    }

    List<CategoryRange> mapSeedsToLocations( CategoryRange seeds ) {
        List<CategoryRange> soils = seedToSoilMap.mapSourcesToDestinations( seeds );
        List<CategoryRange> fertilizers = map( soils, soilToFertilizerMap );
        List<CategoryRange> waters = map( fertilizers, fertilizerToWaterMap );
        List<CategoryRange> lights = map( waters, waterToLightMap );
        List<CategoryRange> temperatures = map( lights, lightToTemperatureMap );
        List<CategoryRange> humidities = map( temperatures, temperatureToHumidityMap );
        List<CategoryRange> locations = map( humidities, humidityToLocationMap );

        return locations;
    }

    private List<CategoryRange> map( List<CategoryRange> inputs, CategoryMap map ) {
        return inputs
            .stream()
            .map( map::mapSourcesToDestinations )
            .flatMap( List::stream )
            .toList();
    }
}
