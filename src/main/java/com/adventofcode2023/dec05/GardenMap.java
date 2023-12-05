package com.adventofcode2023.dec05;

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

    long mapSeedToLocation( long seed ) {
        long soil = seedToSoilMap.mapSourceToDestination( seed );
        long fertilizer = soilToFertilizerMap.mapSourceToDestination( soil );
        long water = fertilizerToWaterMap.mapSourceToDestination( fertilizer );
        long light = waterToLightMap.mapSourceToDestination( water );
        long temperature = lightToTemperatureMap.mapSourceToDestination( light );
        long humidity = temperatureToHumidityMap.mapSourceToDestination( temperature );
        long location = humidityToLocationMap.mapSourceToDestination( humidity );

        return location;
    }
}
