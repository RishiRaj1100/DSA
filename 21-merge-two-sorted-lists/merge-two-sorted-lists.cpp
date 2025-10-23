/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    
    void addElementatEnd(ListNode* &head, ListNode* &tail, ListNode* temp){
        if(head==nullptr){
            head=tail=temp;
        }
        else{
            tail->next = temp;
            tail = tail->next;
        }

    }
    ListNode* mergeTwoLists(ListNode* a, ListNode* b) {
        ListNode* ansHead = nullptr;
        ListNode* ansTail = nullptr;
       
      


        while(a!=nullptr && b!=nullptr){
            if(a->val < b->val){
                ListNode* temp =a;
                a= a->next;
                temp->next = nullptr;
                addElementatEnd(ansHead,ansTail,temp);
                
            }
            else{
               ListNode* temp =b;
               b=b->next;
               temp->next =nullptr;
               addElementatEnd(ansHead, ansTail, temp);

            }
        }

         if (ansTail != nullptr) {
            ansTail->next = (a != nullptr) ? a : b;
        } else {
            ansHead = (a != nullptr) ? a : b;
        }

        return ansHead;
        
    }
};