initMaps()
async function initMaps() {
    await ymaps3.ready

    const {YMap, YMapDefaultSchemeLayer, YMapDefaultFeaturesLayer} = ymaps3
    const {YMapDefaultMarker} = await ymaps3.import('@yandex/ymaps3-markers@0.0.1');

    const moscowMap = new YMap(
        document.getElementById('moscow-map'),
        {
            location: {
                center: [37.545173, 55.645698],
                zoom: 13
            }
        },
        [
            new YMapDefaultFeaturesLayer({}),
            new YMapDefaultSchemeLayer({})
        ]
    )
    moscowMap.addChild(new YMapDefaultMarker({
        coordinates: [37.545173, 55.645698],
        color: '#6203DF',
        popup: {content: 'Москва, Введенского 3к1', position: 'left'}
    }))
}