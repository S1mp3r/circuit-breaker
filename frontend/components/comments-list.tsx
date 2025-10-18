"use client";

import { useState } from "react";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Alert, AlertDescription } from "@/components/ui/alert";
import { AlertCircle, MessageSquare, Send } from "lucide-react";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea";
import { Label } from "@/components/ui/label";
import { Separator } from "@/components/ui/separator";
import { timeStamp } from "console";

interface Comment {
  author: string;
  text: string;
  timestamp: Date;
}

interface CommentsListProps {
  publicationId: string;
  comments: Comment[];
}

export function CommentsList({ publicationId, comments }: CommentsListProps) {
  const [error, setError] = useState<string | null>(null);
  const [isCircuitOpen, setIsCircuitOpen] = useState(false);
  const [submitting, setSubmitting] = useState(false);
  const [submitError, setSubmitError] = useState<string | null>(null);
  const [formData, setFormData] = useState({
    author: "",
    text: "",
  });

  const handleSubmitComment = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      setSubmitting(true);
      setSubmitError(null);

      let data = {
        author: formData.author,
        text: formData.text,
      };

      const response = await fetch(`http://localhost:8082/api/v1/comments`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ publicationId, data }),
      });

      if (!response.ok) {
        throw new Error("Failed to submit comment");
      }

      // Reset form and refresh comments
      setFormData({ author: "", text: "" });
      let comment = {
        ...data,
        timestamp: new Date(Date.now()),
      };
      comments.push(comment);
    } catch (err) {
      setSubmitError(err instanceof Error ? err.message : "An error occurred");
    } finally {
      setSubmitting(false);
    }
  };

  if (error) {
    return (
      <Alert variant="destructive">
        <AlertCircle className="h-4 w-4" />
        <AlertDescription>{error}</AlertDescription>
      </Alert>
    );
  }

  return (
    <Card>
      <CardHeader>
        <div className="flex items-center justify-between">
          <CardTitle className="flex items-center gap-2">
            <MessageSquare className="h-5 w-5" />
            Comments ({comments.length})
          </CardTitle>
          {isCircuitOpen && (
            <Badge variant="secondary" className="gap-1">
              <AlertCircle className="h-3 w-3" />
              Cached Data
            </Badge>
          )}
        </div>
      </CardHeader>
      <CardContent className="space-y-6">
        <form onSubmit={handleSubmitComment} className="space-y-4">
          <div className="space-y-2">
            <Label htmlFor="author">Your Name</Label>
            <Input
              id="author"
              placeholder="Enter your name"
              value={formData.author}
              onChange={(e) =>
                setFormData((prev) => ({ ...prev, author: e.target.value }))
              }
              required
              disabled={submitting}
            />
          </div>

          <div className="space-y-2">
            <Label htmlFor="comment">Your Comment</Label>
            <Textarea
              id="comment"
              placeholder="Write your comment here..."
              value={formData.text}
              onChange={(e) =>
                setFormData((prev) => ({ ...prev, text: e.target.value }))
              }
              rows={3}
              required
              disabled={submitting}
            />
          </div>

          {submitError && (
            <Alert variant="destructive">
              <AlertCircle className="h-4 w-4" />
              <AlertDescription>{submitError}</AlertDescription>
            </Alert>
          )}

          <Button type="submit" disabled={submitting} className="gap-2">
            <Send className="h-4 w-4" />
            {submitting ? "Submitting..." : "Submit Comment"}
          </Button>
        </form>

        <Separator />

        <div className="space-y-4">
          {comments.length === 0 ? (
            <p className="text-center text-sm text-muted-foreground py-4">
              No comments yet. Be the first to comment!
            </p>
          ) : (
            comments.map((comment, index) => (
              <div
                key={index}
                className="rounded-lg border border-border bg-card p-4"
              >
                <p className="text-sm font-semibold text-foreground">
                  {comment.author}
                </p>
                <p className="mt-2 text-sm text-muted-foreground leading-relaxed">
                  {comment.text} -{" "}
                  {new Date(comment.timestamp).toLocaleDateString("pt-BR")}
                </p>
              </div>
            ))
          )}
        </div>
      </CardContent>
    </Card>
  );
}
