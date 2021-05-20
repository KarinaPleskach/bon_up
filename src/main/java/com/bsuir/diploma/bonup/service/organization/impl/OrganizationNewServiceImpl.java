package com.bsuir.diploma.bonup.service.organization.impl;

import com.bsuir.diploma.bonup.dao.organization.OrganizationNewDao;
import com.bsuir.diploma.bonup.dto.model.organization.NewOrganizationWithPhoto;
import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.organization.OrganizationNewDto;
import com.bsuir.diploma.bonup.exception.organization.NoSuchOrganizationException;
import com.bsuir.diploma.bonup.exception.organization.OrganizationWithSuchNameAlreadyExistException;
import com.bsuir.diploma.bonup.exception.validation.NullValidationException;
import com.bsuir.diploma.bonup.model.organization.OrganizationNew;
import com.bsuir.diploma.bonup.model.photo.Photo;
import com.bsuir.diploma.bonup.model.task.additional.Category;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import com.bsuir.diploma.bonup.service.organization.OrganizationNewService;
import com.bsuir.diploma.bonup.service.photo.PhotoService;
import com.bsuir.diploma.bonup.service.task.additional.CategoryService;
import com.bsuir.diploma.bonup.service.user.UserService;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrganizationNewServiceImpl implements OrganizationNewService {

    @Autowired
    private UserService userService;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OrganizationNewDao organizationNewDao;

    @Override
    public void create(OrganizationNewDto organizationNewDto, String lang) {
        UserLogin user = userService.findByToken(organizationNewDto.getToken(), lang);
        if (organizationNewDao.findByTitle(organizationNewDto.getTitle()).isPresent()) {
            throw new OrganizationWithSuchNameAlreadyExistException(lang);
        }

//        final String baseUrl = "http://maps.googleapis.com/maps/api/geocode/json";// путь к Geocoding API по HTTP
//        final Map<String, String> params = new HashMap<>();
//        params.put("sensor", "false");// исходит ли запрос на геокодирование от устройства с датчиком местоположения
//        params.put("address", "Россия, Москва, улица Поклонная, 12");// адрес, который нужно геокодировать
//        final String url = baseUrl + '?' + encodeParams(params);// генерируем путь с параметрами
//        System.out.println(url);// Путь, что бы можно было посмотреть в браузере ответ службы
//        JSONObject response = null;// делаем запрос к вебсервису и получаем от него ответ
//        try {
//            response = JsonReader.read(url);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // как правило наиболее подходящий ответ первый и данные о координатах можно получить по пути
//        // //results[0]/geometry/location/lng и //results[0]/geometry/location/lat
//        JSONObject location = response.getJSONArray("results").getJSONObject(0);
//        location = location.getJSONObject("geometry");
//        location = location.getJSONObject("location");
//        final double lng = location.getDouble("lng");// долгота
//        final double lat = location.getDouble("lat");// широта
//        System.out.println(String.format("%f,%f", lat, lng));// итоговая широта и долгота

        Photo photo = photoService.getPhoto(organizationNewDto.getPhotoId(), lang);
        Category category = categoryService.getById(organizationNewDto.getCategoryId(), lang);
        OrganizationNew organizationNew = OrganizationNew.builder()
                .availableCouponsCount(organizationNewDto.getAvailableCouponsCount())
                .availableStocksCount(organizationNewDto.getAvailableStocksCount())
                .availableTasksCount(organizationNewDto.getAvailableTasksCount())
                .contactsPhone(organizationNewDto.getContactsPhone())
                .contactsVK(organizationNewDto.getContactsVK())
                .contactsWebSite(organizationNewDto.getContactsWebSite())
                .descriptionText(organizationNewDto.getDescriptionText())
                .directorFirstName(organizationNewDto.getDirectorFirstName())
                .directorLastName(organizationNewDto.getDirectorLastName())
                .directorSecondName(organizationNewDto.getDirectorSecondName())
                .locationCity(organizationNewDto.getLocationCity())
                .locationCountry(organizationNewDto.getLocationCountry())
                .userLogin(user)
                .locationHomeNumber(organizationNewDto.getLocationHomeNumber())
                .locationStreet(organizationNewDto.getLocationStreet())
                .photo(photo)
                .title(organizationNewDto.getTitle())
                .category(category)
                .latitude(0.0f)
                .longitude(0.0f)
                .build();
        organizationNewDao.save(organizationNew);
    }

    private static String encodeParams(final Map<String, String> params) {
        final String paramsUrl = Joiner.on('&').join(// получаем значение вида key1=value1&key2=value2...
                Iterables.transform(params.entrySet(), new Function<Map.Entry<String, String>, String>() {

                    @Override
                    public String apply(final Map.Entry<String, String> input) {
                        try {
                            final StringBuffer buffer = new StringBuffer();
                            buffer.append(input.getKey());// получаем значение вида key=value
                            buffer.append('=');
                            buffer.append(URLEncoder.encode(input.getValue(), "utf-8"));// кодируем строку в соответствии со стандартом HTML 4.01
                            return buffer.toString();
                        } catch (final UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }));
        return paramsUrl;
    }

    @Override
    public List<NewOrganizationWithPhoto> getOrganizations(TokenDto tokenUser, String lang) {
        validateTokenUser(tokenUser, lang);
        UserLogin user = userService.findByToken(tokenUser.getToken(), lang);
        return organizationNewDao.findByUserLogin(user).stream()
                .map(o -> {
                    NewOrganizationWithPhoto n = new NewOrganizationWithPhoto();
                    n.setAvailableStocksCount(o.getAvailableStocksCount());
                    n.setAvailableCouponsCount(o.getAvailableCouponsCount());
                    n.setAvailableTasksCount(o.getAvailableTasksCount());
                    n.setCategoryId(o.getCategory().getId());
                    n.setContactsPhone(o.getContactsPhone());
                    n.setContactsVK(o.getContactsVK());
                    n.setContactsWebSite(o.getContactsWebSite());
                    n.setDescriptionText(o.getDescriptionText());
                    n.setId(o.getId());
                    n.setTitle(o.getTitle());
                    n.setLocationCity(o.getLocationCity());
                    n.setLatitude(o.getLatitude());
                    n.setPhotoId(o.getPhoto().getId());
                    n.setLongitude(o.getLongitude());
                    n.setDirectorFirstName(o.getDirectorFirstName());
                    n.setDirectorLastName(o.getDirectorLastName());
                    n.setDirectorSecondName(o.getDirectorSecondName());
                    n.setLocationCountry(o.getLocationCountry());
                    n.setLocationStreet(o.getLocationStreet());
                    n.setLocationHomeNumber(o.getLocationHomeNumber());
                    return n;
                })
                .collect(Collectors.toList());
    }

    private void validateTokenUser(TokenDto tokenDto, String lang) {
        if (Objects.isNull(tokenDto) || Objects.isNull(tokenDto.getToken())) {
            throw new NullValidationException(lang);
        }
    }

    @Override
    public OrganizationNew findByNameAndUser(String name, UserLogin userLogin, String lang) {
        return organizationNewDao.findByTitleAndUserLogin(name, userLogin)
                .orElseThrow(() -> new NoSuchOrganizationException(lang));
    }

    @Override
    public OrganizationNew findByIdAndUser(Long id, UserLogin userLogin, String lang) {
        return organizationNewDao.findByIdAndUserLogin(id, userLogin)
                .orElseThrow(() -> new NoSuchOrganizationException(lang));
    }

    @Override
    public void modify(OrganizationNewDto organizationNewDto, String lang) {
        UserLogin user = userService.findByToken(organizationNewDto.getToken(), lang);

        OrganizationNew organizationNew = findByNameAndUser(organizationNewDto.getTitle(), user, lang);

        Photo photo = photoService.getPhoto(organizationNewDto.getPhotoId(), lang);
        Category category = categoryService.getById(organizationNewDto.getCategoryId(), lang);
        OrganizationNew.builder()
                .availableCouponsCount(organizationNewDto.getAvailableCouponsCount())
                .availableStocksCount(organizationNewDto.getAvailableStocksCount())
                .availableTasksCount(organizationNewDto.getAvailableTasksCount())
                .contactsPhone(organizationNewDto.getContactsPhone())
                .contactsVK(organizationNewDto.getContactsVK())
                .contactsWebSite(organizationNewDto.getContactsWebSite())
                .descriptionText(organizationNewDto.getDescriptionText())
                .directorFirstName(organizationNewDto.getDirectorFirstName())
                .directorLastName(organizationNewDto.getDirectorLastName())
                .directorSecondName(organizationNewDto.getDirectorSecondName())
                .locationCity(organizationNewDto.getLocationCity())
                .locationCountry(organizationNewDto.getLocationCountry())
                .userLogin(user)
                .locationHomeNumber(organizationNewDto.getLocationHomeNumber())
                .locationStreet(organizationNewDto.getLocationStreet())
                .photo(photo)
                .title(organizationNewDto.getTitle())
                .category(category)
                .latitude(0.0f)
                .longitude(0.0f)
                .build();
    }
}

class JsonReader {

    private static String readAll(final Reader rd) throws IOException {
        final StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject read(final String url) throws IOException, JSONException {
        final InputStream is = new URL(url).openStream();
        try {
            final BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            final String jsonText = readAll(rd);
            final JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}