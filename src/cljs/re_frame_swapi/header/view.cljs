(ns re-frame-swapi.header.view)

(defn container []
  [:div {:style {:with "100%"
                 :min-height "50px"
                 :background "#567890"
                 :font-size "20px"}}
   [:a {:href "#/"
        :style {:margin "10px"}} "Planets"]
   [:a {:href "#/exercise"} "Exercise"]])