(ns re-frame-swapi.home.view
  (:require
    [re-frame.core :as rf]
    [re-frame-swapi.home.events]
    [re-frame-swapi.home.subs]))

(defn loading-component []
  [:h3 {:style {:margin "30%"
                :text-align :center}}
   "Welcome to the SWAPI, Please wait loading!"])

(defn planet-button [name]
  [:button {:style {:border           :none
                    :outline          0
                    :display          :inline-block
                    :padding          "15px"
                    :color            :white
                    :background-color "#000"
                    :text-align       :center
                    :cursor           :pointer
                    :width            "100%"
                    :font-size        "18px"}} name])

(defn planet-header [{:keys [name terrain gravity]}]
  [:div {:style {:background :black
                 :padding "15px"}}
   [:h1 {:style {:text-align :center}} name]
   [:p {:style {:text-align :center
                :color      :gray
                :font-size  "20px"}} terrain]
   [:p {:style {:text-align :center}} gravity]])

(defn planet-details [{:keys [label value]}]
  [:div {:style {:display   :flex
                 :flex-wrap :wrap}}
   [:p {:style {:text-decoration :none
                :font-size       "16px"
                :margin 5
                :color           :gray}} (str label ":")]
   [:p {:style {:text-decoration :none
                :font-size       "18px"
                :padding-left "10px"
                :margin 5
                :color           :black}} value]])

(defn planet-container [planet]
  [:div {:style {:box-shadow  "0 4px 8px 0 rgba(0, 0, 0, 0.2)"
                 :width       "350px"
                 :margin      "20px"
                 :font-family :arial
                 :color       :white}}
   [planet-header {:name    (:name planet)
                   :terrain (:terrain planet)
                   :gravity (:gravity planet)}]
   [planet-details {:label "Rotation Period"
                    :value (:rotation-period planet)}]
   [planet-details {:label "Orbital Period"
                    :value (:orbital-period planet)}]
   [planet-details {:label "Diameter"
                    :value (:diameter planet)}]
   [planet-details {:label "Surface Water"
                    :value (:surface-water planet)}]
   [planet-details {:label "Population"
                    :value (:population planet)}]
   [planet-details {:label "Climate"
                    :value (:climate planet)}]
   [planet-button "Films & Residents"]])


(defn pagination []
  (let [previous- (rf/subscribe [:swapi-planet-previous])
        next- (rf/subscribe [:swapi-planet-next])]
    (println @previous-)
  [:div {:style {:display         :flex
                 :flex-wrap       :wrap
                 :justify-content :center}}
   [:button {:style {:color      :white
                     :float      :left
                     :padding    "8px 16px"
                     :font-size  "20px"
                     :background (if (nil? @previous-) :gray :black)
                     :border     "1px solid #ddd"}
             :disabled (nil? @previous-)
             :on-click #(rf/dispatch [:swapi-planets/get @previous-])}
      [:b "<< Previous"]]
   [:button {:style {:color      :white
                     :float      :left
                     :padding    "8px 16px"
                     :font-size  "20px"
                     :background (if (nil? @next-) :gray :black)
                     :border     "1px solid #ddd"}
             :disabled (nil? @next-)
             :on-click #(rf/dispatch [:swapi-planets/get @next-])}
      [:b "Next >>"]]]))

(defn planets-component [planets]
  [:div
   [:div {:style {:display         :flex
                  :flex-wrap       :wrap
                  :justify-content :center
                  :margin          "20px"}}
    (map (fn [plaent]
           ^{:key plaent} [planet-container plaent]) planets)]
   [pagination]])

(defn container []
  (let [planets- (rf/subscribe [:swapi-planets])]
    [:div
     (if (= @planets- :loading)
       [loading-component]
       [planets-component @planets-])]))


