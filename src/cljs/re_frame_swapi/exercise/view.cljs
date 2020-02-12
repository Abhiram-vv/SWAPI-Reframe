(ns re-frame-swapi.exercise.view
  (:require
    [re-frame.core :as re-frame]
    [re-frame-swapi.subs :as subs]
    [reagent.core :as r]
    ))

(def tick-count (r/atom 0))

(defn counter []
  [:input {:type "button"
           :value "Click me to count!"
           :on-click #(swap! tick-count inc)}])

(defn counting-component [tick-count]
  [:h4 tick-count])

(defn input-component [value]
  [:input {:type :text
           :value @value
           :on-change #(reset! value (-> % .-target .-value) )}]
  )

(defn input-state []
  (let [value-  (r/atom "ready")]
    (fn []
      [:div
       [:p  "The input value here is: " [:strong {:style {:color "red"}} @value-]]
       [:p "Change the input value here:" [input-component value-]]])))


(defonce timer (r/atom (js/Date.)))

(defonce time-color (r/atom "#f34"))

(defonce time-updater (js/setInterval #(reset! timer (js/Date.)) 1000))

(defn greeting [message]
  [:h1 message])

(defn clock []
  (let [time-str (-> @timer .toTimeString (clojure.string/split " ") first)]
    [:div
     {:style {:color @time-color :font-size  "30px" :align :center :width "100%"}}
     time-str]))

(defn color-input []
  [:div.color-input
   "Time color: "
   [:input {:type "text"
            :value @time-color
            :on-change #(reset! time-color (-> % .-target .-value))}]])

(defn timer-container []
  [:div
   [greeting "Hello world, it is now"]
   [clock]
   [color-input]])

(defn main-panel []
  [:div
   [timer-container]
   [counting-component @tick-count]
   [counter]
   [ input-state]])
