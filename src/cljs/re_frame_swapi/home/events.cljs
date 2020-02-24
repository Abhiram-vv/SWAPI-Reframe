(ns re-frame-swapi.home.events
  (:require
    [re-frame.core :as rf]
    [re-frame-swapi.db :as db]
    [ajax.core :as ajax]
    [camel-snake-kebab.core :refer [->kebab-case-keyword]]
    [camel-snake-kebab.extras :refer [transform-keys]]))

(rf/reg-event-db
  :success-get-result
  (fn [db [_ result]]
    (-> db
        (assoc-in [:swapi :planets] (:results (transform-keys ->kebab-case-keyword result)))
        (assoc-in [:swapi :count] (:count (transform-keys ->kebab-case-keyword result)))
        (assoc-in [:swapi :next] (:next (transform-keys ->kebab-case-keyword result)))
        (assoc-in [:swapi :previous] (:previous (transform-keys ->kebab-case-keyword result))))))

(rf/reg-event-db
  :failure-get-result
  (fn [db [_ result]]
    (assoc-in db [:swapi :planets] result)))

(rf/reg-event-db
  :swapi-planets/get
  (fn [db [_ url]]
    (ajax/GET url {:handler       #(rf/dispatch [:success-get-result %])
                                              :error-handler #(rf/dispatch [:failure-get-result %])})
    (assoc-in db [:swapi :planets] :loading)))