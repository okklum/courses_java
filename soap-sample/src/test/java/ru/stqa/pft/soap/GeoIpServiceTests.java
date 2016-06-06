package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by alisa on 06.06.2016.
 */
public class GeoIpServiceTests {

  @Test

  public void testMyIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("128.199.41.247");
    assertEquals(geoIP.getCountryName(), "European Union");
  }

  @Test
  public void testInvalidIp() {
    /*GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("104.236.89.93");
    а вот тут ServerSOAPFaultException :) */
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("128.199.41.ххх");
    assertEquals(geoIP.getCountryCode(), "EU");
  }
}
