package com.study.springmvc.coursework5.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class LottoService {
    public static final List<Set<Integer>> lottos = new ArrayList<>();

    public List<Set<Integer>> getLottos() {
        return lottos;
    }

    public void addLotto() {
        lottos.add(0, generateLotto());
    }

    public void updateLotto(int index) {
        lottos.set(index, generateLotto());
    }

    private Set<Integer> generateLotto() {
        Random r = new Random();
        // 樂透 539：1~39 取出不重複的5個號碼
        Set<Integer> lotto = new HashSet<>();
        while (lotto.size() < 5) {
            lotto.add(r.nextInt(39) + 1);
        }
        return lotto;
    }

    public void deleteLotto(int index) {
        lottos.remove(index);
    }

    public Map<Integer, Long> groupAndCountLottos(List<Set<Integer>> lottos) {
        Map<Integer, Long> times = lottos.stream()
                .flatMap(Set::stream)
                // Counting duplicate numbers
                .collect(groupingBy(Integer::intValue, counting()))
                // Start sorting by value
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                // Collect sorted entries and map them to LinkedHashMap to maintain order
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (aLong, aLong2) -> aLong2, LinkedHashMap::new));
        return times;
    }
}
