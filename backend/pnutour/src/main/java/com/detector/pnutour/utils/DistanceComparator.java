package com.detector.pnutour.utils;

import com.detector.pnutour.entity.Structure;

import java.util.Comparator;

public class DistanceComparator implements Comparator<Structure> {
    private double referenceLatitude;
    private double referenceLongitude;

    public DistanceComparator(double referenceLatitude, double referenceLongitude) {
        this.referenceLatitude = referenceLatitude;
        this.referenceLongitude = referenceLongitude;
    }

    @Override
    public int compare(Structure structure1, Structure structure2) {
        double distance1 = calculateDistance(structure1);
        double distance2 = calculateDistance(structure2);

        // 거리를 기준으로 오름차순으로 정렬
        return Double.compare(distance1, distance2);
    }

    private double calculateDistance(Structure structure) {
        // 좌표 간의 거리 계산 로직을 구현하세요.
        // 여기서는 간단히 두 좌표 사이의 유클리드 거리를 계산하는 방법을 사용합니다.
        double lat1 = Double.parseDouble(structure.getLatitude());
        double lon1 = Double.parseDouble(structure.getLongitude());
        double lat2 = referenceLatitude;
        double lon2 = referenceLongitude;

        double earthRadius = 6371; // 지구 반지름 (km)

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }
}

