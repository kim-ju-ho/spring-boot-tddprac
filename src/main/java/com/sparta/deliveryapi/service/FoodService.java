package com.sparta.deliveryapi.service;

import com.sparta.deliveryapi.dto.FoodDto;
import com.sparta.deliveryapi.models.Food;
import com.sparta.deliveryapi.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodService {
    private final FoodRepository foodRepository;

    @Transactional
    public List<Food> registFood(List<FoodDto> foodDto, Long restaurantId) {
        List<Food> foods =new ArrayList<>();

        for(FoodDto i : foodDto){
            // 가격 검증
            priceChk(i.getPrice());
            //
            nameChk(i.getName(),restaurantId);
            foods.add(new Food(i,restaurantId));
        }
        inputNameChk(foods);
        foodRepository.saveAll(foods);
    return foods;

    }
    // 메뉴 뽑기
    public List<Food> getMenu(Long restaurantId) {
        List<Food> foods =foodRepository.findAllByRestaurantId(restaurantId);

        return foods;
    }
    // 가격 체크
    public void priceChk(int price){
        if(price <100 || price >1000000){
            throw new IllegalArgumentException("가격 오류");
        }else if(price %100 != 0){
            throw new IllegalArgumentException("가격 오류");
        }
    }
    // 기존에 저장된 음식명 중복 체크
    public void nameChk(String name,Long restaurantId){
        if(foodRepository.findAllByRestaurantIdAndName(restaurantId,name).size() > 0){
            throw new IllegalArgumentException("이미 등록된 음식명입니다.");
        }
    }
    //입력된 음식명 중복 체크
    public void inputNameChk(List<Food> foods){
        String[] chk = new String[foods.size()];
        for(int i =0;i<foods.size();i++){
            chk[i] = foods.get(i).getName();
        }
        if(chk.length != Arrays.stream(chk).distinct().count()){
            throw new IllegalArgumentException("같은 음식이름이 들어가있습니다.");
        }


    }
}
